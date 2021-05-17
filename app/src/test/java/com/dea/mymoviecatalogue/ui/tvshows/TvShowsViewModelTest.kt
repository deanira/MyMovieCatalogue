package com.dea.mymoviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.TvShowResponse
import com.dea.mymoviecatalogue.data.response.TvShowResultsItem
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
class TvShowsViewModelTest {
    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var mainRepository: FakeMainRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<TvShowResultsItem>>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(mainRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPopularTvShows() =
        coroutineTestRule.runBlockingTest {
            val dummyTvShows = DataDummyTest.generateTvShows()
            val tvShows = MutableLiveData<Resource<TvShowResponse>>()
            tvShows.value = dummyTvShows
            `when`(mainRepository.getPopularShows()).thenReturn(tvShows.value)
            val tvShowsEntities = viewModel.getPopularTvShows().value
            verify(mainRepository).getPopularShows()
            assertNotNull(tvShowsEntities)
            assertEquals(5, tvShowsEntities?.size)

            viewModel.getPopularTvShows().observeForever(observer)
            verify(observer).onChanged(dummyTvShows.data?.results)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun getOnTheAirTvShows() =
        coroutineTestRule.runBlockingTest {
            val dummyTvShows = DataDummyTest.generateTvShows()
            val tvShows = MutableLiveData<Resource<TvShowResponse>>()
            tvShows.value = dummyTvShows
            `when`(mainRepository.getOnTheAirShows()).thenReturn(tvShows.value)
            val tvShowsEntities = viewModel.getOnTheAirTvShows().value
            verify(mainRepository).getOnTheAirShows()
            assertNotNull(tvShowsEntities)
            assertEquals(5, tvShowsEntities?.size)

            viewModel.getOnTheAirTvShows().observeForever(observer)
            verify(observer).onChanged(dummyTvShows.data?.results)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun getTopRatedTvShows() =
        coroutineTestRule.runBlockingTest {
            val dummyTvShows = DataDummyTest.generateTvShows()
            val tvShows = MutableLiveData<Resource<TvShowResponse>>()
            tvShows.value = dummyTvShows
            `when`(mainRepository.getOnTheAirShows()).thenReturn(tvShows.value)
            val tvShowsEntities = viewModel.getOnTheAirTvShows().value
            verify(mainRepository).getOnTheAirShows()
            assertNotNull(tvShowsEntities)
            assertEquals(5, tvShowsEntities?.size)

            viewModel.getOnTheAirTvShows().observeForever(observer)
            verify(observer).onChanged(dummyTvShows.data?.results)
        }
}