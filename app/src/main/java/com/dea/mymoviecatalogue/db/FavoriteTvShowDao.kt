package com.dea.mymoviecatalogue.db

import androidx.paging.DataSource
import androidx.room.*
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow

@Dao
interface FavoriteTvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(tvShow : FavoriteTvShow)

    @Query("SELECT * FROM FavoriteMovie")
    fun getAllData() : DataSource.Factory<Int, FavoriteTvShow>

    @Query("SELECT * FROM FavoriteMovie WHERE movieId=:id")
    suspend fun isFavorite(id : Int) : FavoriteTvShow

    @Delete
    suspend fun deleteFavorite(tvShow: FavoriteTvShow)
}