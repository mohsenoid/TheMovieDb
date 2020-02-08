package com.mohsenoid.themoviedb.presentation.view.details.movie

import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.TrailerModel
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import timber.log.Timber

class MovieDetailsPresenter(
    private val configProvider: ConfigProvider,
    private val repository: Repository,
    private val trailerModelMapper: Mapper<TrailerEntity, TrailerModel>
) : MovieDetailsContract.Presenter {

    private var view: MovieDetailsContract.View? = null

    override fun bind(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override suspend fun loadTrailer(id: Int) {
        view?.showLoading()
        fetchTrailer(id)
    }

    private suspend fun fetchTrailer(id: Int) {
        if (!configProvider.isOnline()) {
            view?.showOfflineMessage(isCritical = false)
        }

        try {
            val trailerEntity: TrailerEntity? = repository.getMovieTrailer(id)

            if (trailerEntity != null) {
                val trailerModel: TrailerModel = trailerModelMapper.map(trailerEntity)

                view?.setTrailerResult(trailerModel)
            }
        } catch (e: Exception) {
            Timber.w(e)

            view?.showMessage(e.message ?: e.toString())
        } finally {
            view?.hideLoading()
        }
    }
}
