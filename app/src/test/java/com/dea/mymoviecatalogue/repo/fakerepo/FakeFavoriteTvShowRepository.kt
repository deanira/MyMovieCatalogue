package com.dea.mymoviecatalogue.repo.fakerepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteTvShowRepository

class FakeFavoriteTvShowRepository: FavoriteTvShowRepository {
    private val tvFavouriteItem = mutableListOf<FavoriteTvShow>()

    private val observableMovieFavouriteItem =
        MutableLiveData<List<FavoriteTvShow>>(tvFavouriteItem)

    private val list: DataSource.Factory<Int, FavoriteTvShow> = ListDataSource(tvFavouriteItem)

    private fun refreshLiveData() {
        observableMovieFavouriteItem.postValue(tvFavouriteItem)
    }

    override suspend fun insertFavourite(tvShow: FavoriteTvShow) {

    }

    override fun getAllData(): LiveData<PagedList<FavoriteTvShow>> {

    }

    override suspend fun isFavourite(idTvShow: Int): FavoriteTvShow {

    }

    override suspend fun deleteFavourite(tvShow: FavoriteTvShow) {

    }
}