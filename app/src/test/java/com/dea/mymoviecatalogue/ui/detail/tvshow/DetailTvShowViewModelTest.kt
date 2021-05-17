package com.dea.mymoviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.repo.FakeMainRepository
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var tvShowViewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummyTest.generateDetailTvShow()
    private val tvShowId = 0

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var mainRepository: FakeMainRepository

    @Mock
    private lateinit var observer: Observer<DetailTvShowResponse>

    @Before
    fun setUp() {
        tvShowViewModel = DetailTvShowViewModel(mainRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun getTvShow() = runBlocking {
        val tvShow = MutableLiveData<Resource<DetailTvShowResponse>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(mainRepository.getTvShowById(tvShowId)).thenReturn(tvShow.value)
        val tvShowEntity = tvShowViewModel.getTvShow(tvShowId).value as DetailTvShowResponse
        verify(mainRepository).getTvShowById(tvShowId)
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(dummyTvShow.data?.id, tvShowEntity.id)
        Assert.assertEquals(dummyTvShow.data?.name, tvShowEntity.name)
        Assert.assertEquals(dummyTvShow.data?.voteAverage!!, tvShowEntity.voteAverage!!, 0.1)
        Assert.assertEquals(dummyTvShow.data?.type, tvShowEntity.type)
        Assert.assertEquals(dummyTvShow.data?.backdropPath, tvShowEntity.backdropPath)
        Assert.assertEquals(dummyTvShow.data?.originalLanguage, tvShowEntity.originalLanguage)
        Assert.assertEquals(dummyTvShow.data?.originalName, tvShowEntity.originalName)
        Assert.assertEquals(dummyTvShow.data?.overview, tvShowEntity.overview)
        Assert.assertEquals(dummyTvShow.data?.lastAirDate, tvShowEntity.lastAirDate)
        Assert.assertEquals(dummyTvShow.data?.popularity, tvShowEntity.popularity)

        tvShowViewModel.tvShowDetail.observeForever(observer)
        verify(observer).onChanged(dummyTvShow.data)
    }
}