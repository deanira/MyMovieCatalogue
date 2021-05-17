package com.dea.mymoviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.MovieResponse
import com.dea.mymoviecatalogue.data.response.MovieResultsItem
import com.dea.mymoviecatalogue.repo.FakeMainRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var mainRepository: FakeMainRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<MovieResultsItem>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(mainRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPopularMovies() =
        coroutineTestRule.runBlockingTest {
            val dummyMovies = DataDummyTest.generateMovies()
            val movies = MutableLiveData<Resource<MovieResponse>>()
            movies.value = dummyMovies
            `when`(mainRepository.getPopularMovies()).thenReturn(movies.value)
            val movieEntities = viewModel.getPopularMovies().value
            verify(mainRepository).getPopularMovies()
            assertNotNull(movieEntities)
            assertEquals(5, movieEntities?.size)

            viewModel.getPopularMovies().observeForever(observer)
            verify(observer).onChanged(dummyMovies.data?.results)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun getNowPlayingMovies() =
        coroutineTestRule.runBlockingTest {
            val dummyMovies = DataDummyTest.generateMovies()
            val movies = MutableLiveData<Resource<MovieResponse>>()
            movies.value = dummyMovies
            `when`(mainRepository.getNowPlayingMovies()).thenReturn(movies.value)
            val movieEntities = viewModel.getNowPlayingMovies().value
            verify(mainRepository).getNowPlayingMovies()
            assertNotNull(movieEntities)
            assertEquals(5, movieEntities?.size)

            viewModel.getNowPlayingMovies().observeForever(observer)
            verify(observer).onChanged(dummyMovies.data?.results)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun getTopRatedMovies() =
        coroutineTestRule.runBlockingTest {
            val dummyMovies = DataDummyTest.generateMovies()
            val movies = MutableLiveData<Resource<MovieResponse>>()
            movies.value = dummyMovies
            `when`(mainRepository.getTopRatedMovies()).thenReturn(movies.value)
            val movieEntities = viewModel.getTopRatedMovies().value
            verify(mainRepository).getTopRatedMovies()
            assertNotNull(movieEntities)
            assertEquals(5, movieEntities?.size)

            viewModel.getTopRatedMovies().observeForever(observer)
            verify(observer).onChanged(dummyMovies.data?.results)
        }
}