package com.mohsenoid.themoviedb.test

import com.mohsenoid.themoviedb.data.network.dto.TrendingTvShowsResponse
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import retrofit2.Response

object TvShowDataFactory {

    object Network {

        fun trendingResponse(tvShowResults: List<TvShowResult> = makeTvShowResults(5)): Response<TrendingTvShowsResponse> {

            return Response.success(
                TrendingTvShowsResponse(
                    page = DataFactory.randomInt(),
                    tvShowResults = tvShowResults,
                    totalPages = DataFactory.randomInt(),
                    totalResults = DataFactory.randomInt()
                )
            )
        }

        fun makeTvShowResult(id: Int = DataFactory.randomInt()): TvShowResult {
            return TvShowResult(
                id = id,
                name = DataFactory.randomString(),
                firstAirDate = DataFactory.randomString(),
                overview = DataFactory.randomString(),
                voteAverage = DataFactory.randomDouble(0.0, 10.0),
                posterPath = DataFactory.randomString()
            )
        }

        fun makeTvShowResults(count: Int): List<TvShowResult> {
            val characters: MutableList<TvShowResult> = ArrayList()
            for (i: Int in 0 until count) {
                val tvShowResult: TvShowResult = makeTvShowResult()
                characters.add(tvShowResult)
            }
            return characters
        }
    }

    object Entity {

        fun makeTvShow(id: Int = DataFactory.randomInt()): TvShowEntity {
            return TvShowEntity(
                id = id,
                name = DataFactory.randomString(),
                firstAirDate = DataFactory.randomString(),
                overview = DataFactory.randomString(),
                voteAverage = DataFactory.randomDouble(0.0, 10.0),
                posterUrl = DataFactory.randomString()
            )
        }

        fun makeTvShows(count: Int): List<TvShowEntity> {
            val characters: MutableList<TvShowEntity> = ArrayList()
            for (i: Int in 0 until count) {
                val character: TvShowEntity = makeTvShow()
                characters.add(character)
            }
            return characters
        }
    }
}
