package com.dea.mymoviecatalogue.repo.repointerface

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow

interface FavoriteTvShowRepository {
    suspend fun insertFavourite(tvShow: FavoriteTvShow)

    fun getAllData(): LiveData<PagedList<FavoriteTvShow>>

    suspend fun isFavourite(idTvShow: Int): FavoriteTvShow

    suspend fun deleteFavourite(tvShow: FavoriteTvShow)
}
