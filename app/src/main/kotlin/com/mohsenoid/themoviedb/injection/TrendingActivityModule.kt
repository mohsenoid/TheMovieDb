package com.mohsenoid.themoviedb.injection

import com.mohsenoid.themoviedb.presentation.mapper.MovieModelMapper
import com.mohsenoid.themoviedb.presentation.mapper.TvShowModelMapper
import com.mohsenoid.themoviedb.presentation.view.trending.movie.TrendingMovieContract
import com.mohsenoid.themoviedb.presentation.view.trending.movie.TrendingMovieListFragment
import com.mohsenoid.themoviedb.presentation.view.trending.movie.TrendingMoviePresenter
import com.mohsenoid.themoviedb.presentation.view.trending.tvshow.TrendingTvShowContract
import com.mohsenoid.themoviedb.presentation.view.trending.tvshow.TrendingTvShowListFragment
import com.mohsenoid.themoviedb.presentation.view.trending.tvshow.TrendingTvShowPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val trendingActivityModule = module {

    scope(named<TrendingMovieListFragment>()) {

        scoped<TrendingMovieContract.Presenter> {
            TrendingMoviePresenter(
                configProvider = get(),
                repository = get(),
                movieModelMapper = get(named<MovieModelMapper>())
            )
        }
    }

    scope(named<TrendingTvShowListFragment>()) {

        scoped<TrendingTvShowContract.Presenter> {
            TrendingTvShowPresenter(
                configProvider = get(),
                repository = get(),
                tvShowModelMapper = get(named<TvShowModelMapper>())
            )
        }
    }
}
