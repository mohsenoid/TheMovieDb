package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper

class TvShowEntityMapper : Mapper<TvShowResult, TvShowEntity> {

    override fun map(input: TvShowResult): TvShowEntity {
        return TvShowEntity(
            id = input.id,
            name = input.name,
            firstAirDate = input.firstAirDate,
            overview = input.overview,
            voteAverage = input.voteAverage,
            posterUrl = "${NetworkConstants.POSTER_URL_BASE_URL}${input.posterPath}"
        )
    }
}
