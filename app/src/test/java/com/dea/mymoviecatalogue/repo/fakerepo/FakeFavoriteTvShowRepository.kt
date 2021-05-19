package com.dea.mymoviecatalogue.repo.fakerepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.ListDataSource
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteTvShowRepository
import com.dea.mymoviecatalogue.utils.DataDummyTest

class FakeFavoriteTvShowRepository: FavoriteTvShowRepository {
    private val tvFavoriteItem = mutableListOf<FavoriteTvShow>()

    private val observableMovieFavouriteItem =
        MutableLiveData<List<FavoriteTvShow>>(tvFavoriteItem)

    private val list: DataSource.Factory<Int, FavoriteTvShow> = ListDataSource(tvFavoriteItem)

    private fun refreshLiveData() {
        observableMovieFavouriteItem.postValue(tvFavoriteItem)
    }

    override suspend fun insertFavourite(tvShow: FavoriteTvShow) {
        tvFavoriteItem.add(tvShow)
        refreshLiveData()
    }

    override fun getAllData(): LiveData<PagedList<FavoriteTvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(50)
            .setPageSize(50)
            .build()
        return LivePagedListBuilder(list, config).build()
    }

    override suspend fun isFavourite(idTvShow: Int): FavoriteTvShow {
        DataDummyTest.generateFavouriteTv().forEach {
            tvFavoriteItem.add(it)
        }
        refreshLiveData()
        var movie = FavoriteTvShow(null, idTvShow, "", "", 0.0, "", "", "")
        tvFavoriteItem.forEach {
            if (it.tvShowId == idTvShow) movie = it
        }
        return movie
    }

    override suspend fun deleteFavourite(tvShow: FavoriteTvShow) {
        tvFavoriteItem.remove(tvShow)
        refreshLiveData()
    }
}