package com.mohsenoid.themoviedb.test

import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.data.network.dto.TrendingMoviesResponse
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import retrofit2.Response

object MovieDataFactory {

    object Network {

        fun trendingResponse(movieResults: List<MovieResult> = makeMovieResults(5)): Response<TrendingMoviesResponse> {

            return Response.success(
                TrendingMoviesResponse(
                    page = DataFactory.randomInt(),
                    movieResults = movieResults,
                    totalPages = DataFactory.randomInt(),
                    totalResults = DataFactory.randomInt()
                )
            )
        }

        fun makeMovieResult(id: Int = DataFactory.randomInt()): MovieResult {
            return MovieResult(
                id = id,
                title = DataFactory.randomString(),
                releaseDate = DataFactory.randomString(),
                overview = DataFactory.randomString(),
                voteAverage = DataFactory.randomDouble(0.0, 10.0),
                posterPath = DataFactory.randomString()
            )
        }

        fun makeMovieResults(count: Int): List<MovieResult> {
            val characters: MutableList<MovieResult> = ArrayList()
            for (i: Int in 0 until count) {
                val movieResult: MovieResult = makeMovieResult()
                characters.add(movieResult)
            }
            return characters
        }
    }

    object Entity {

        fun makeMovie(id: Int = DataFactory.randomInt()): MovieEntity {
            return MovieEntity(
                id = id,
                title = DataFactory.randomString(),
                releaseDate = DataFactory.randomString(),
                overview = DataFactory.randomString(),
                voteAverage = DataFactory.randomDouble(0.0, 10.0),
                posterUrl = DataFactory.randomString()
            )
        }

        fun makeMovies(count: Int): List<MovieEntity> {
            val characters: MutableList<MovieEntity> = ArrayList()
            for (i: Int in 0 until count) {
                val character: MovieEntity = makeMovie()
                characters.add(character)
            }
            return characters
        }
    }

    object Model {

        fun makeMovie(id: Int = DataFactory.randomInt()): MovieModel {
            return MovieModel(
                id = id,
                title = DataFactory.randomString(),
                releaseDate = DataFactory.randomString(),
                overview = DataFactory.randomString(),
                voteAverage = DataFactory.randomDouble(0.0, 10.0),
                posterUrl = DataFactory.randomString()
            )
        }

        fun makeMovies(count: Int): List<MovieModel> {
            val characters: MutableList<MovieModel> = ArrayList()
            for (i: Int in 0 until count) {
                val character: MovieModel = makeMovie()
                characters.add(character)
            }
            return characters
        }
    }
}
