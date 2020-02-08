package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper

class MovieEntityMapper : Mapper<MovieResult, MovieEntity> {

    override fun map(input: MovieResult): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            overview = input.overview,
            voteAverage = input.voteAverage,
            posterUrl = "${NetworkConstants.POSTER_URL_BASE_URL}${input.posterPath}"
        )
    }
}
