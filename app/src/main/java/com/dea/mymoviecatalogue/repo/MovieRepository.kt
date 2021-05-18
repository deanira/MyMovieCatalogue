package com.dea.mymoviecatalogue.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.db.FavoriteMovieDao
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteMovieRepository
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
    @Named("favoriteMovieDao") private val favoriteMovieDao: FavoriteMovieDao
) : @Named("movieRepo") FavoriteMovieRepository {
    override suspend fun insertFavourite(movie: FavoriteMovie) {
        favoriteMovieDao.insertFavourite(movie)
    }

    override fun getAllData(): LiveData<PagedList<FavoriteMovie>> {
        val config =PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(50)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(favoriteMovieDao.getAllData(), config).build()
    }

    override suspend fun isFavourite(idMovie: Int): FavoriteMovie =
        favoriteMovieDao.isFavorite(idMovie)

    override suspend fun deleteFavourite(movie: FavoriteMovie) =
        favoriteMovieDao.deleteFavorite(movie)
}