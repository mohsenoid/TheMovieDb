package com.mohsenoid.themoviedb.presentation.view.trending.movie

import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.mohsenoid.themoviedb.test.DataFactory
import com.mohsenoid.themoviedb.test.MovieDataFactory
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.Verify
import org.amshove.kluent.VerifyNoFurtherInteractions
import org.amshove.kluent.When
import org.amshove.kluent.called
import org.amshove.kluent.calling
import org.amshove.kluent.itAnswers
import org.amshove.kluent.itReturns
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TrendingMoviePresenterTest {

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var configProvider: ConfigProvider

    @Mock
    lateinit var movieModelMapper: Mapper<MovieEntity, MovieModel>

    @Mock
    lateinit var view: TrendingMovieContract.View

    private lateinit var presenter: TrendingMovieContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TrendingMoviePresenter(configProvider, repository, movieModelMapper)
        presenter.bind(view)
    }

    @Test
    fun `test if loadMovies calls view showLoading`() {
        runBlocking {
            // GIVEN

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.showLoading() was called
        }
    }

    @Test
    fun `test if isOffline loadMovies calls view showOfflineMessage`() {
        runBlocking {
            // GIVEN
            stubConfigProviderIsOnline(false)

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.showOfflineMessage(isCritical = false) was called
        }
    }

    @Test
    fun `test loadMovies calls repository getTrendingMoviesOfWeek`() {
        runBlocking {
            // GIVEN

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on repository that repository.getTrendingMoviesOfWeek() was called
            VerifyNoFurtherInteractions on repository
        }
    }

    @Test
    fun `test loadMovies calls view setMoviesResult OnSuccess`() {
        runBlocking {
            // GIVEN
            val count = 5
            val movieEntities: List<MovieEntity> = MovieDataFactory.Entity.makeMovies(count)
            stubRepositoryGetTrendingMoviesOfWeekOnSuccess(movieEntities)

            val movieModels: List<MovieModel> = MovieDataFactory.Model.makeMovies(count)
            repeat(count) { i -> stubMovieModelMapperMap(movieEntities[i], movieModels[i]) }

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.setMoviesResult(result = movieModels) was called
        }
    }

    @Test
    fun `test loadMovies calls view showMessage OnError`() {
        runBlocking {
            // GIVEN
            val errorMessage = DataFactory.randomString()
            stubRepositoryGetTrendingMoviesOfWeekOnError(Exception(errorMessage))

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.showMessage(message = errorMessage) was called
        }
    }

    @Test
    fun `test loadMovies calls view hideLoading OnSuccess`() {
        runBlocking {
            // GIVEN
            val movies: List<MovieEntity> = MovieDataFactory.Entity.makeMovies(5)
            stubRepositoryGetTrendingMoviesOfWeekOnSuccess(movies)

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.hideLoading() was called
        }
    }

    @Test
    fun `test loadMovies calls view hideLoading OnError`() {
        runBlocking {
            // GIVEN
            stubRepositoryGetTrendingMoviesOfWeekOnError(Exception())

            // WHEN
            presenter.loadMovies()

            // THEN
            Verify on view that view.hideLoading() was called
        }
    }

    private fun stubConfigProviderIsOnline(isOnline: Boolean) {
        When calling configProvider.isOnline() itReturns isOnline
    }

    private fun stubMovieModelMapperMap(input: MovieEntity, output: MovieModel) {
        When calling movieModelMapper.map(input) itReturns output
    }

    private suspend fun stubRepositoryGetTrendingMoviesOfWeekOnSuccess(movies: List<MovieEntity>) {
        When calling repository.getTrendingMoviesOfWeek() itReturns movies
    }

    private suspend fun stubRepositoryGetTrendingMoviesOfWeekOnError(exception: Exception) {
        When calling repository.getTrendingMoviesOfWeek() itAnswers { throw exception }
    }
}
