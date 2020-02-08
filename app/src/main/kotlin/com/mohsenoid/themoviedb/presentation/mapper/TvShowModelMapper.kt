package com.mohsenoid.themoviedb.presentation.mapper

import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.TvShowModel

class TvShowModelMapper : Mapper<TvShowEntity, TvShowModel> {

    override fun map(input: TvShowEntity): TvShowModel {
        return TvShowModel(
            name = input.name,
            firstAirDate = input.firstAirDate,
            overview = input.overview,
            voteAverage = input.voteAverage,
            posterUrl = input.posterUrl
        )
    }
}
