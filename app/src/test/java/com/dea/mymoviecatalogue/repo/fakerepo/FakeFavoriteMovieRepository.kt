package com.dea.mymoviecatalogue.repo.fakerepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteMovieRepository

class FakeFavoriteMovieRepository: FavoriteMovieRepository {
    private val movieFavoriteItem = mutableListOf<FavoriteMovie>()

    private val observableMovieFavouriteItem =
        MutableLiveData<List<FavoriteMovie>>(movieFavoriteItem)

    private val list: DataSource.Factory<Int, FavoriteMovie> = ListDataSource(movieFavoriteItem)

    private fun refreshLiveData() {
        observableMovieFavouriteItem.postValue(movieFavoriteItem)
    }

    override suspend fun insertFavourite(movie: FavoriteMovie) {

    }

    override fun getAllData(): LiveData<PagedList<FavoriteMovie>> {

    }

    override suspend fun isFavourite(idMovie: Int): FavoriteMovie {

    }

    override suspend fun deleteFavourite(movie: FavoriteMovie) {

    }
}