package com.mohsenoid.themoviedb.presentation.view.trending.movie

import com.mohsenoid.themoviedb.presentation.base.BasePresenter
import com.mohsenoid.themoviedb.presentation.base.BaseView
import com.mohsenoid.themoviedb.presentation.model.MovieModel

interface TrendingMovieContract {

    interface View : BaseView {

        fun setMoviesResult(result: List<MovieModel>)
    }

    interface Presenter : BasePresenter<View> {

        suspend fun loadMovies()
    }
}
