package com.dea.mymoviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.myTvShowcatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteTvShowRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import com.dea.mymoviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FavoriteTvShowViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteTvShow>>

    @Mock
    lateinit var observer2 : Observer<FavoriteTvShow>

    @Mock
    private lateinit var repository: FakeFavoriteTvShowRepository

    private lateinit var viewModel: FavoriteTvShowViewModel

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(50)
        .setPageSize(50)
        .build()

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(repository)
    }

    @Test
    fun getFavoriteTvShows() = runBlockingTest {
        val dummyFavouriteTvShow: DataSource.Factory<Int, FavoriteTvShow> =
            ListDataSource(DataDummyTest.generateFavouriteTv())
        val favourite = MutableLiveData<PagedList<FavoriteTvShow>>()
        favourite.value = LiveDataTestUtil.getOrAwaitValueLiveData(
            LivePagedListBuilder(
                dummyFavouriteTvShow,
                config
            ).build()
        )

        Mockito.`when`(repository.getAllData()).thenReturn(favourite)
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.getFavoriteTvShows())
        verify(repository).getAllData()

        assertNotNull(value)
        assertEquals(2, value.size)
        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(value)
        print(value)
    }

    @Test
    fun isFavoriteTvShows () = runBlockingTest {
        val dummyFavouriteTvShow = DataDummyTest.generateFavouriteTv()[0]
        val favourite = MutableLiveData<FavoriteTvShow>()
        favourite.value = dummyFavouriteTvShow

        Mockito.`when`(repository.isFavourite(1)).thenReturn(favourite.value)
        viewModel.isFavoriteTvShow(1)
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.isFav)
        verify(repository).isFavourite(1)

        assertNotNull(value)
        assertEquals(dummyFavouriteTvShow, value)
        viewModel.isFav.observeForever(observer2)
        verify(observer2).onChanged(value)
    }

    @Test
    fun deleteFromFavorite() = runBlockingTest {
        viewModel.deleteFromFavorite(FavoriteTvShow(1, 1, "", "", 0.0, "", "", ""))
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.deleteFavoriteTvShowStatus)

        assertEquals(value.message, "Item removed from favorite")
        print(value.data)
    }

    @Test
    fun insertToFavorite() = runBlockingTest {
        viewModel.insertToFavorite(FavoriteTvShow(4, 4, "", "", 0.0, "", "", ""))
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.insertFavoriteTvShowStatus)

        assertEquals(value.message, "Item added to favorite")
        print(value.data)
    }
}