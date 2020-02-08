package com.mohsenoid.themoviedb.presentation.mapper

import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.MovieModel

class MovieModelMapper : Mapper<MovieEntity, MovieModel> {

    override fun map(input: MovieEntity): MovieModel {
        return MovieModel(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            overview = input.overview,
            voteAverage = input.voteAverage,
            posterUrl = input.posterUrl
        )
    }
}
