package com.mohsenoid.themoviedb.data

import com.mohsenoid.themoviedb.data.exception.NoResultException
import com.mohsenoid.themoviedb.data.exception.ServerException
import com.mohsenoid.themoviedb.data.network.NetworkClient
import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.data.network.dto.TrendingMoviesResponse
import com.mohsenoid.themoviedb.data.network.dto.TrendingTvShowsResponse
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.data.network.dto.VideosResponse
import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImpl(
    private val apiKey: String,
    private val networkClient: NetworkClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val movieEntityMapper: Mapper<MovieResult, MovieEntity>,
    private val tvShowEntityMapper: Mapper<TvShowResult, TvShowEntity>,
    private val trailerEntityMapper: Mapper<VideoResult, TrailerEntity>
) : Repository {

    override suspend fun getTrendingMoviesOfWeek(): List<MovieEntity> {
        return withContext(ioDispatcher) {
            val trendingMoviesResult: List<MovieResult> = fetchNetworkTrendingMovies()
            val movieEntities: List<MovieEntity> =
                trendingMoviesResult.map(movieEntityMapper::map)

            if (movieEntities.isEmpty()) throw NoResultException()

            return@withContext movieEntities
        }
    }

    private suspend fun fetchNetworkTrendingMovies(): List<MovieResult> {
        val trendingMoviesResponse: Response<TrendingMoviesResponse> =
            networkClient.trendingMovies(apiKey)

        if (trendingMoviesResponse.isSuccessful) {
            val moviesResponse: TrendingMoviesResponse? = trendingMoviesResponse.body()

            moviesResponse ?: throw ServerException(
                code = trendingMoviesResponse.code(),
                error = "Response body is empty!"
            )

            return moviesResponse.movieResults
        } else {
            throw ServerException(
                code = trendingMoviesResponse.code(),
                error = trendingMoviesResponse.errorBody().toString()
            )
        }
    }

    override suspend fun getTrendingTvShowsOfWeek(): List<TvShowEntity> {
        return withContext(ioDispatcher) {
            val trendingTvShowsResult: List<TvShowResult> = fetchNetworkTrendingTvShows()
            val tvShowEntities: List<TvShowEntity> =
                trendingTvShowsResult.map(tvShowEntityMapper::map)

            if (tvShowEntities.isEmpty()) throw NoResultException()

            return@withContext tvShowEntities
        }
    }

    private suspend fun fetchNetworkTrendingTvShows(): List<TvShowResult> {
        val trendingTvShowsResponse: Response<TrendingTvShowsResponse> =
            networkClient.trendingTvShows(apiKey)

        if (trendingTvShowsResponse.isSuccessful) {
            val tvShowsResponse: TrendingTvShowsResponse? = trendingTvShowsResponse.body()

            tvShowsResponse ?: throw ServerException(
                code = trendingTvShowsResponse.code(),
                error = "Response body is empty!"
            )

            return tvShowsResponse.tvShowResults
        } else {
            throw ServerException(
                code = trendingTvShowsResponse.code(),
                error = trendingTvShowsResponse.errorBody().toString()
            )
        }
    }

    override suspend fun getMovieTrailer(movieId: Int): TrailerEntity? {
        return withContext(ioDispatcher) {
            val videosResult: List<VideoResult> = fetchNetworkMovieTrailer(movieId)
            val videoResult: VideoResult = videosResult.firstOrNull {
                it.type == NetworkConstants.QUERY_PARAM_VIDEO_TYPE_TRAILER && it.site == NetworkConstants.QUERY_PARAM_VIDEO_SITE_YOUTUBE
            } ?: return@withContext null

            return@withContext trailerEntityMapper.map(videoResult)
        }
    }

    private suspend fun fetchNetworkMovieTrailer(movieId: Int): List<VideoResult> {
        val videosResponse: Response<VideosResponse> =
            networkClient.movieTrailer(movieId, NetworkConstants.QUERY_PARAM_LANGUAGE_EN_US, apiKey)

        if (videosResponse.isSuccessful) {
            val response: VideosResponse? = videosResponse.body()

            response ?: throw ServerException(
                code = videosResponse.code(),
                error = "Response body is empty!"
            )

            return response.results
        } else {
            throw ServerException(
                code = videosResponse.code(),
                error = videosResponse.errorBody().toString()
            )
        }
    }
}
