package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.test.MovieDataFactory
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MovieEntityMapperTest {

    private lateinit var mapper: Mapper<MovieResult, MovieEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = MovieEntityMapper()
    }

    @Test
    fun map() {
        // GIVEN
        val movieResult: MovieResult = MovieDataFactory.Network.makeMovieResult()

        val expectedMovieEntity = MovieEntity(
            id = movieResult.id,
            title = movieResult.title,
            releaseDate = movieResult.releaseDate,
            overview = movieResult.overview,
            voteAverage = movieResult.voteAverage,
            posterUrl = "${NetworkConstants.POSTER_URL_BASE_URL}${movieResult.posterPath}"
        )

        // WHEN
        val actualMovieEntity: MovieEntity = mapper.map(movieResult)

        // THEN
        expectedMovieEntity shouldEqual actualMovieEntity
    }
}
