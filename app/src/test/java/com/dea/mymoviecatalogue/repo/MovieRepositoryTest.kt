package com.dea.mymoviecatalogue.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteMovieRepository
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteTvShowRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import com.dea.mymoviecatalogue.utils.DataDummyTest
import com.dea.mymoviecatalogue.utils.LiveDataTestUtil
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest  {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    private val repository = FakeFavoriteMovieRepository()

    @Test
    fun insertFavourite() = runBlockingTest {
        //insert 2 item to db
        DataDummyTest.generateFavouriteMovie().forEachIndexed { index, _ ->
            repository.insertFavourite(FavoriteMovie(index + 2, index + 2, "", "", 0.0, "", "", ""))
        }
        val data = LiveDataTestUtil.getOrAwaitValueLiveData(repository.getAllData())

        //after insert data will be there
        Assert.assertNotNull(data)
        assertEquals(DataDummyTest.generateFavouriteMovie().size, data.size)
    }

    @Test
    fun getAllData() = runBlockingTest {
        DataDummyTest.generateFavouriteMovie().forEach {
            repository.insertFavourite(it)
        }
        val data = LiveDataTestUtil.getOrAwaitValueLiveData(repository.getAllData())

        //after insert data will be there
        Assert.assertNotNull(data)
        assertEquals(DataDummyTest.generateFavouriteMovie().size, data.size)
    }

    @Test
    fun isFavourite() = runBlockingTest {
        val favouriteMovie =  repository.isFavourite(1)
        Assert.assertNotNull(favouriteMovie.id)
        print(favouriteMovie)
    }

    @Test
    fun deleteFavourite() = runBlockingTest {
        repository.deleteFavourite(DataDummyTest.generateFavouriteMovie()[0])
        val data = LiveDataTestUtil.getOrAwaitValueLiveData(repository.getAllData())
        Assert.assertNotNull(data)
        assertEquals(DataDummyTest.generateFavouriteMovie().size-1, data.size)
    }
}
