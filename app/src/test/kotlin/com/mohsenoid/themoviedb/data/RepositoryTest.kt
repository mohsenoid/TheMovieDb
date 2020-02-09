package com.mohsenoid.themoviedb.data

import com.mohsenoid.rickandmorty.util.dispatcher.DispatcherProvider
import com.mohsenoid.themoviedb.data.mapper.MovieEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TrailerEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TvShowEntityMapper
import com.mohsenoid.themoviedb.data.network.NetworkClient
import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.test.DataFactory
import com.mohsenoid.themoviedb.test.TestDispatcherProvider
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class RepositoryTest {

    @Mock
    lateinit var networkClient: NetworkClient

    @Mock
    private lateinit var configProvider: ConfigProvider

    lateinit var repository: Repository

    private val testDispatcherProvider: DispatcherProvider = TestDispatcherProvider()

    private val movieEntityMapper: Mapper<MovieResult, MovieEntity> = MovieEntityMapper()
    private val tvShowEntityMapper: Mapper<TvShowResult, TvShowEntity> = TvShowEntityMapper()
    private val trailerEntityMapper: Mapper<VideoResult, TrailerEntity> = TrailerEntityMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repository = RepositoryImpl(
            apiKey = DataFactory.randomString(),
            networkClient = networkClient,
            ioDispatcher = testDispatcherProvider.ioDispatcher,
            movieEntityMapper = movieEntityMapper,
            tvShowEntityMapper = tvShowEntityMapper,
            trailerEntityMapper = trailerEntityMapper
        )
    }

    fun stubConfigProviderIsOnline(isOnline: Boolean) {
        When calling configProvider.isOnline() itReturns isOnline
    }
}
