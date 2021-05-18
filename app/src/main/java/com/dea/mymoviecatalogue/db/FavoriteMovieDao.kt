package com.dea.mymoviecatalogue.db

import androidx.paging.DataSource
import androidx.room.*
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(movie : FavoriteMovie)

    @Query("SELECT * FROM FavoriteMovie")
    fun getAllData() : DataSource.Factory<Int, FavoriteMovie>

    @Query("SELECT * FROM FavoriteMovie WHERE movieId=:id")
    suspend fun isFavorite(id : Int) : FavoriteMovie

    @Delete
    suspend fun deleteFavorite(movie: FavoriteMovie)
}