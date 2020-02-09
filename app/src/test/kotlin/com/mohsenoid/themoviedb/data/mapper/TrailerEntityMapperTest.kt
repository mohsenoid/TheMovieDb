package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.test.TrailerDataFactory
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class TrailerEntityMapperTest {

    private lateinit var mapper: Mapper<VideoResult, TrailerEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = TrailerEntityMapper()
    }

    @Test
    fun map() {
        // GIVEN
        val videoResult: VideoResult = TrailerDataFactory.Network.makeVideoResult()

        val expectedTrailerEntity = TrailerEntity(
            id = videoResult.id,
            name = videoResult.name,
            url = "${NetworkConstants.YOUTUBE_URL_BASE_URL}${videoResult.key}"
        )

        // WHEN
        val actualTrailerEntity: TrailerEntity = mapper.map(videoResult)

        // THEN
        expectedTrailerEntity shouldEqual actualTrailerEntity
    }
}
