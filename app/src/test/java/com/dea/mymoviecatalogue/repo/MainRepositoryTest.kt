package com.dea.mymoviecatalogue.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dea.mymoviecatalogue.repo.fakerepo.FakeMainRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val mainRepository = FakeMainRepository()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val movieResponse = DataDummyTest.generateMovies()
    private val tvShowResponse = DataDummyTest.generateTvShows()
    private val movieId = movieResponse.data?.results?.get(0)?.id
    private val tvShowId = tvShowResponse.data?.results?.get(0)?.id

    @Test
    fun getPopularMovies() = coroutineTestRule.runBlockingTest {
        val movieEntities = mainRepository.getPopularMovies().data
        assertNotNull(movieEntities)
        assertEquals(movieResponse.data?.results?.size?.toLong(), movieEntities?.results?.size?.toLong())
    }

    @Test
    fun getNowPlayingMovies() = coroutineTestRule.runBlockingTest {
        val movieEntities = mainRepository.getNowPlayingMovies().data
        assertNotNull(movieEntities)
        assertEquals(movieResponse.data?.results?.size?.toLong(), movieEntities?.results?.size?.toLong())
    }

    @Test
    fun getTopRatedMovies() = coroutineTestRule.runBlockingTest {
        val movieEntities = mainRepository.getTopRatedMovies().data
        assertNotNull(movieEntities)
        assertEquals(movieResponse.data?.results?.size?.toLong(), movieEntities?.results?.size?.toLong())
    }

    @Test
    fun getMovieById() = coroutineTestRule.runBlockingTest {
        val detail = mainRepository.getMovieById(movieId!!)
        assertNotNull(detail)
        //disini hanya mengecek salah satu value saja
        assertEquals("Mortal Kombat", detail.data?.title)
    }

    @Test
    fun getPopularShows() = coroutineTestRule.runBlockingTest {
        val tvShowEntities = mainRepository.getPopularShows().data
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.data?.results?.size?.toLong(), tvShowEntities?.results?.size?.toLong())
    }

    @Test
    fun getOnTheAirShows() = coroutineTestRule.runBlockingTest {
        val tvShowEntities = mainRepository.getOnTheAirShows().data
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.data?.results?.size?.toLong(), tvShowEntities?.results?.size?.toLong())
    }

    @Test
    fun getTopRatedShows() = coroutineTestRule.runBlockingTest {
        val tvShowEntities = mainRepository.getTopRatedShows().data
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.data?.results?.size?.toLong(), tvShowEntities?.results?.size?.toLong())
    }

    @Test
    fun getTvShowById() = coroutineTestRule.runBlockingTest {
        val detail = mainRepository.getTvShowById(tvShowId!!)
        assertNotNull(detail)
        //disini hanya mengecek salah satu value saja
        assertEquals("The Falcon and the Winter Soldier", detail.data?.name)
    }
}