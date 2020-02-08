package com.mohsenoid.themoviedb.injection

import com.mohsenoid.themoviedb.presentation.mapper.TrailerModelMapper
import com.mohsenoid.themoviedb.presentation.view.details.movie.MovieDetailsActivity
import com.mohsenoid.themoviedb.presentation.view.details.movie.MovieDetailsContract
import com.mohsenoid.themoviedb.presentation.view.details.movie.MovieDetailsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieDetailsActivityModule = module {

    scope(named<MovieDetailsActivity>()) {

        scoped<MovieDetailsContract.Presenter> {
            MovieDetailsPresenter(
                configProvider = get(),
                repository = get(),
                trailerModelMapper = get(named<TrailerModelMapper>())
            )
        }
    }
}
