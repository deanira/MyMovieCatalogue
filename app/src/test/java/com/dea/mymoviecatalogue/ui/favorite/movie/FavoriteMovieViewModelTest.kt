package com.dea.mymoviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteMovieRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import com.dea.mymoviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
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
class FavoriteMovieViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteMovie>>

    @Mock
    lateinit var observer2 : Observer<FavoriteMovie>

    @Mock
    private lateinit var repository: FakeFavoriteMovieRepository

    private lateinit var viewModel: FavoriteMovieViewModel

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(50)
        .setPageSize(50)
        .build()

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() = runBlockingTest {
        val dummyFavouriteMovie: DataSource.Factory<Int, FavoriteMovie> =
            ListDataSource(DataDummyTest.generateFavouriteMovie())
        val favourite = MutableLiveData<PagedList<FavoriteMovie>>()
        favourite.value = LiveDataTestUtil.getOrAwaitValueLiveData(
            LivePagedListBuilder(
                dummyFavouriteMovie,
                config
            ).build()
        )

        Mockito.`when`(repository.getAllData()).thenReturn(favourite)
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.getFavoriteMovies())
        verify(repository).getAllData()

        Assert.assertNotNull(value)
        Assert.assertEquals(2, value.size)
        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(value)
        print(value)
    }

    @Test
    fun isFavoriteMovie() = runBlockingTest {
        val dummyFavouriteMovie = DataDummyTest.generateFavouriteMovie()[0]
        val favourite = MutableLiveData<FavoriteMovie>()
        favourite.value = dummyFavouriteMovie

        Mockito.`when`(repository.isFavourite(1)).thenReturn(favourite.value)
        viewModel.isFavoriteMovie(1)
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.isFav)
        verify(repository).isFavourite(1)

        Assert.assertNotNull(value)
        assertEquals(dummyFavouriteMovie, value)
        viewModel.isFav.observeForever(observer2)
        verify(observer2).onChanged(value)
    }

    @Test
    fun deleteFromFavorite() = runBlockingTest {
        viewModel.deleteFromFavorite(FavoriteMovie(1, 1, "", "", 0.0, "", "", ""))
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.deleteFavoriteMovieStatus)

        Assert.assertEquals(value.message, "Item removed from favorite")
        print(value.data)
    }

    @Test
    fun insertToFavorite() = runBlockingTest {
        viewModel.insertToFavorite(FavoriteMovie(4, 4, "", "", 0.0, "", "", ""))
        val value = LiveDataTestUtil.getOrAwaitValueLiveData(viewModel.insertFavoriteMovieStatus)

        Assert.assertEquals(value.message, "Item added to favorite")
        print(value.data)
    }
}