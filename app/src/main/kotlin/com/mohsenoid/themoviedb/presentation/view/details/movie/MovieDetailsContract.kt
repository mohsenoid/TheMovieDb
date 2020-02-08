package com.mohsenoid.themoviedb.presentation.view.details.movie

import com.mohsenoid.themoviedb.presentation.base.BasePresenter
import com.mohsenoid.themoviedb.presentation.base.BaseView
import com.mohsenoid.themoviedb.presentation.model.TrailerModel

interface MovieDetailsContract {

    interface View : BaseView {

        fun setTrailerResult(trailerModel: TrailerModel)
    }

    interface Presenter : BasePresenter<View> {

        suspend fun loadTrailer(id: Int)
    }
}
