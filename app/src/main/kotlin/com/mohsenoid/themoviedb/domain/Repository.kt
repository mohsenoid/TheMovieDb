package com.mohsenoid.themoviedb.domain

import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity

interface Repository {

    suspend fun getTrendingMoviesOfWeek(): List<MovieEntity>

    suspend fun getTrendingTvShowsOfWeek(): List<TvShowEntity>

    suspend fun getMovieTrailer(movieId: Int): TrailerEntity?
}
