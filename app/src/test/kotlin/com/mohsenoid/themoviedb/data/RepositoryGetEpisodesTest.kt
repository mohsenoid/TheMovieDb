package com.mohsenoid.themoviedb.data

import com.mohsenoid.themoviedb.data.network.dto.TrendingMoviesResponse
import com.mohsenoid.themoviedb.test.MovieDataFactory
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.Verify
import org.amshove.kluent.VerifyNoFurtherInteractions
import org.amshove.kluent.When
import org.amshove.kluent.any
import org.amshove.kluent.called
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.Test
import retrofit2.Response

class RepositoryGetEpisodesTest : RepositoryTest() {

    @Test
    fun `test if getTrendingMoviesOfWeek calls networkClient`() {
        runBlocking {
            // GIVEN
            stubConfigProviderIsOnline(isOnline = true)
            stubNetworkClientTrendingMovies(MovieDataFactory.Network.trendingResponse())

            // WHEN
            repository.getTrendingMoviesOfWeek()

            // THEN
            Verify on networkClient that networkClient.trendingMovies(apiKey = any()) was called
            VerifyNoFurtherInteractions on networkClient
        }
    }

    private suspend fun stubNetworkClientTrendingMovies(response: Response<TrendingMoviesResponse>) {
        When calling networkClient.trendingMovies(apiKey = any()) itReturns response
    }
}
