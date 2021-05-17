package com.dea.mymoviecatalogue.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteMovie::class, FavouriteTvShow::class],
    version = 2
)

abstract class FavoriteDatabase : RoomDatabase() {

}