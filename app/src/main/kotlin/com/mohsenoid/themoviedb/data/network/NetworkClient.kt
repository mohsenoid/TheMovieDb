package com.mohsenoid.themoviedb.data.network

import com.mohsenoid.themoviedb.data.network.dto.TrendingMoviesResponse
import com.mohsenoid.themoviedb.data.network.dto.TrendingTvShowsResponse
import com.mohsenoid.themoviedb.data.network.dto.VideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkClient {

    @GET(ENDPOINT_TRENDING_MOVIES)
    suspend fun trendingMovies(
        @Query(QUERY_API_KEY) apiKey: String
    ): Response<TrendingMoviesResponse>

    @GET(ENDPOINT_TRENDING_TV_SHOWS)
    suspend fun trendingTvShows(
        @Query(QUERY_API_KEY) apiKey: String
    ): Response<TrendingTvShowsResponse>

    @GET(ENDPOINT_VIDEOS)
    suspend fun movieTrailer(
        @Path(PATH_MOVIE_ID) movieId: Int,
        @Query(QUERY_LANGUAGE) language: String,
        @Query(QUERY_API_KEY) apiKey: String
    ): Response<VideosResponse>

    companion object {
        const val ENDPOINT_TRENDING_MOVIES: String = "trending/movie/week"
        const val ENDPOINT_TRENDING_TV_SHOWS: String = "trending/tv/week"

        const val PATH_MOVIE_ID: String = "movie_id"
        const val ENDPOINT_VIDEOS: String = "movie/{$PATH_MOVIE_ID}/videos"

        const val QUERY_API_KEY = "api_key"
        const val QUERY_LANGUAGE = "language"
    }
}
