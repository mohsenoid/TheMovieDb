package com.mohsenoid.themoviedb.presentation.view.trending.tvshow

import com.mohsenoid.themoviedb.presentation.base.BasePresenter
import com.mohsenoid.themoviedb.presentation.base.BaseView
import com.mohsenoid.themoviedb.presentation.model.TvShowModel

interface TrendingTvShowContract {

    interface View : BaseView {

        fun setTvShowsResult(result: List<TvShowModel>)
    }

    interface Presenter : BasePresenter<View> {

        suspend fun loadTvShows()
    }
}
