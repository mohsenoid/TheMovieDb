package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.test.TvShowDataFactory
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class TvShowEntityMapperTest {

    private lateinit var mapper: Mapper<TvShowResult, TvShowEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = TvShowEntityMapper()
    }

    @Test
    fun map() {
        // GIVEN
        val tvShowResult: TvShowResult = TvShowDataFactory.Network.makeTvShowResult()

        val expectedTvShowEntity = TvShowEntity(
            id = tvShowResult.id,
            name = tvShowResult.name,
            firstAirDate = tvShowResult.firstAirDate,
            overview = tvShowResult.overview,
            voteAverage = tvShowResult.voteAverage,
            posterUrl = "${NetworkConstants.POSTER_URL_BASE_URL}${tvShowResult.posterPath}"
        )

        // WHEN
        val actualTvShowEntity: TvShowEntity = mapper.map(tvShowResult)

        // THEN
        expectedTvShowEntity shouldEqual actualTvShowEntity
    }
}
