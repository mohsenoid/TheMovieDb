package com.mohsenoid.themoviedb.test

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.data.network.dto.VideosResponse
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import retrofit2.Response

object TrailerDataFactory {

    object Network {

        fun trendingResponse(videoResults: List<VideoResult> = makeVideoResults(5)): Response<VideosResponse> {

            return Response.success(
                VideosResponse(
                    id = DataFactory.randomInt(),
                    results = videoResults
                )
            )
        }

        fun makeVideoResult(id: Int = DataFactory.randomInt()): VideoResult {
            return VideoResult(
                id = id.toString(),
                name = DataFactory.randomString(),
                key = DataFactory.randomString(),
                site = NetworkConstants.QUERY_PARAM_VIDEO_SITE_YOUTUBE,
                type = NetworkConstants.QUERY_PARAM_VIDEO_TYPE_TRAILER
            )
        }

        fun makeVideoResults(count: Int): List<VideoResult> {
            val characters: MutableList<VideoResult> = ArrayList()
            for (i: Int in 0 until count) {
                val videoResult: VideoResult =
                    makeVideoResult()
                characters.add(videoResult)
            }
            return characters
        }
    }

    object Entity {

        fun makeTrailer(id: Int = DataFactory.randomInt()): TrailerEntity {
            return TrailerEntity(
                id = id.toString(),
                name = DataFactory.randomString(),
                url = DataFactory.randomString()
            )
        }

        fun makeTrailers(count: Int): List<TrailerEntity> {
            val characters: MutableList<TrailerEntity> = ArrayList()
            for (i: Int in 0 until count) {
                val character: TrailerEntity = makeTrailer()
                characters.add(character)
            }
            return characters
        }
    }
}
