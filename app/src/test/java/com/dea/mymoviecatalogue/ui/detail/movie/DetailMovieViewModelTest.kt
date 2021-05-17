package com.dea.mymoviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.repo.FakeMainRepository
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var movieViewModel: DetailMovieViewModel
    private val dummyMovie = DataDummyTest.generateDetailMovie()
    private val movieId = 0

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var mainRepository: FakeMainRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieResponse>

    @Before
    fun setUp() {
        movieViewModel = DetailMovieViewModel(mainRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun getMovie() = runBlocking {
        val movie = MutableLiveData<Resource<DetailMovieResponse>>()
        movie.value = dummyMovie

        `when`(mainRepository.getMovieById(movieId)).thenReturn(movie.value)
        val movieEntity = movieViewModel.getMovie(movieId).value as DetailMovieResponse
        verify(mainRepository).getMovieById(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.data?.id, movieEntity.id)
        assertEquals(dummyMovie.data?.title, movieEntity.title)
        assertEquals(dummyMovie.data!!.voteAverage, movieEntity.voteAverage, 0.1)
        assertEquals(dummyMovie.data?.adult, movieEntity.adult)
        assertEquals(dummyMovie.data?.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovie.data?.originalLanguage, movieEntity.originalLanguage)
        assertEquals(dummyMovie.data?.originalTitle, movieEntity.originalTitle)
        assertEquals(dummyMovie.data?.overview, movieEntity.overview)
        assertEquals(dummyMovie.data?.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.data?.popularity, movieEntity.popularity)

        movieViewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(dummyMovie.data)
    }
}