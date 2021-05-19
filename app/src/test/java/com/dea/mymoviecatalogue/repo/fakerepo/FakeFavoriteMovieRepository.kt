package com.dea.mymoviecatalogue.repo.fakerepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteMovieRepository
import com.dea.mymoviecatalogue.utils.DataDummyTest

class FakeFavoriteMovieRepository: FavoriteMovieRepository {
    private val movieFavoriteItem = mutableListOf<FavoriteMovie>()

    private val observableMovieFavouriteItem =
        MutableLiveData<List<FavoriteMovie>>(movieFavoriteItem)

    private val list: DataSource.Factory<Int, FavoriteMovie> = ListDataSource(movieFavoriteItem)

    private fun refreshLiveData() {
        observableMovieFavouriteItem.postValue(movieFavoriteItem)
    }

    override suspend fun insertFavourite(movie: FavoriteMovie) {
        movieFavoriteItem.add(movie)
        refreshLiveData()
    }

    override fun getAllData(): LiveData<PagedList<FavoriteMovie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(50)
            .setPageSize(50)
            .build()
        return LivePagedListBuilder(list, config).build()
    }

    override suspend fun isFavourite(idMovie: Int): FavoriteMovie {
        DataDummyTest.generateFavouriteMovie().forEach {
            movieFavoriteItem.add(it)
        }
        refreshLiveData()
        var movie = FavoriteMovie(null, idMovie, "", "", 0.0, "", "", "")
        movieFavoriteItem.forEach {
            if (it.movieId == idMovie) movie = it
        }
        return movie
    }

    override suspend fun deleteFavourite(movie: FavoriteMovie) {
        DataDummyTest.generateFavouriteMovie().forEach {
            movieFavoriteItem.add(it)
        }
        movieFavoriteItem.remove(movie)
        refreshLiveData()
    }
}