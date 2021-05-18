package com.dea.mymoviecatalogue.di

import android.content.Context
import androidx.room.Room
import com.dea.mymoviecatalogue.data.api.ApiService
import com.dea.mymoviecatalogue.db.FavoriteDatabase
import com.dea.mymoviecatalogue.db.FavoriteMovieDao
import com.dea.mymoviecatalogue.db.FavoriteTvShowDao
import com.dea.mymoviecatalogue.repo.MainRepository
import com.dea.mymoviecatalogue.repo.MovieRepository
import com.dea.mymoviecatalogue.repo.TvShowRepository
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteMovieRepository
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteTvShowRepository
import com.dea.mymoviecatalogue.repo.repointerface.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        api: ApiService
    ) = MainRepository(api) as Repository

    @Singleton
    @Provides
    @Named("tvRepo")
    fun provideFavouriteRepository(
        @Named("favouriteTvDao") tvShowDao: FavoriteTvShowDao
    ) = TvShowRepository(tvShowDao) as FavoriteTvShowRepository

    @Singleton
    @Provides
    @Named("movieRepo")
    fun provideFavouriteMovieRepository(
        @Named("favouriteMovieDao") movieDao: FavoriteMovieDao
    ) = MovieRepository(movieDao) as FavoriteMovieRepository

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteDatabase::class.java,
        "favourite_db.db"
    ).build()

    @Singleton
    @Provides
    @Named("favouriteTvDao")
    fun provideFavouriteTvDao(db: FavoriteDatabase) = db.getFavoriteTvShowDao()

    @Singleton
    @Provides
    @Named("favouriteMovieDao")
    fun provideFavouriteMovieDao(db: FavoriteDatabase) = db.getFavoriteMovieDao()
}