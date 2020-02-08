package com.mohsenoid.themoviedb.presentation.view.trending.tvshow

import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.TvShowModel
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import timber.log.Timber

class TrendingTvShowPresenter(
    private val configProvider: ConfigProvider,
    private val repository: Repository,
    private val tvShowModelMapper: Mapper<TvShowEntity, TvShowModel>
) : TrendingTvShowContract.Presenter {

    private var view: TrendingTvShowContract.View? = null

    override fun bind(view: TrendingTvShowContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override suspend fun loadTvShows() {
        view?.showLoading()
        fetchTrendingTvShows()
    }

    private suspend fun fetchTrendingTvShows() {
        if (!configProvider.isOnline()) {
            view?.showOfflineMessage(isCritical = false)
        }

        try {
            val tvShowEntities: List<TvShowEntity> = repository.getTrendingTvShowsOfWeek()
            val tvShowModels: List<TvShowModel> = tvShowEntities.map(tvShowModelMapper::map)

            view?.setTvShowsResult(tvShowModels)
        } catch (e: Exception) {
            Timber.w(e)

            view?.showMessage(e.message ?: e.toString())
        } finally {
            view?.hideLoading()
        }
    }
}
