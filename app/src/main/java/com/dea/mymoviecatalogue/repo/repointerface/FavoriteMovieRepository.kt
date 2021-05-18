package com.dea.mymoviecatalogue.repo.repointerface

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie

interface FavoriteMovieRepository {
    suspend fun insertFavourite(movie: FavoriteMovie)

    fun getAllData(): LiveData<PagedList<FavoriteMovie>>

    suspend fun isFavourite(idMovie: Int): FavoriteMovie

    suspend fun deleteFavourite(movie: FavoriteMovie)
}