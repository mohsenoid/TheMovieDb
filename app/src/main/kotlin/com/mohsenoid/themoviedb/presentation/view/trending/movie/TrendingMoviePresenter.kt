package com.mohsenoid.themoviedb.presentation.view.trending.movie

import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import timber.log.Timber

class TrendingMoviePresenter(
    private val configProvider: ConfigProvider,
    private val repository: Repository,
    private val movieModelMapper: Mapper<MovieEntity, MovieModel>
) : TrendingMovieContract.Presenter {

    private var view: TrendingMovieContract.View? = null

    override fun bind(view: TrendingMovieContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override suspend fun loadMovies() {
        view?.showLoading()
        fetchTrendingMovies()
    }

    private suspend fun fetchTrendingMovies() {
        if (!configProvider.isOnline()) {
            view?.showOfflineMessage(isCritical = false)
        }

        try {
            val movieEntities: List<MovieEntity> = repository.getTrendingMoviesOfWeek()
            val movieModel: List<MovieModel> = movieEntities.map(movieModelMapper::map)

            view?.setMoviesResult(movieModel)
        } catch (e: Exception) {
            Timber.w(e)

            view?.showMessage(e.message ?: e.toString())
        } finally {
            view?.hideLoading()
        }
    }
}
