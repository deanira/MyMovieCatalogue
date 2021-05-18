package com.dea.mymoviecatalogue.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow

@Database(
    entities = [FavoriteMovie::class, FavoriteTvShow::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteMovieDao(): FavoriteMovieDao

    abstract fun getFavoriteTvShowDao(): FavoriteTvShowDao

}