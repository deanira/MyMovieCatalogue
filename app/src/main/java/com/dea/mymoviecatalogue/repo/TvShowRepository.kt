package com.dea.mymoviecatalogue.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.db.FavoriteTvShowDao
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteTvShowRepository
import javax.inject.Inject
import javax.inject.Named

class TvShowRepository @Inject constructor(
    @Named("favoriteTvShowDao") private val favoriteTvShowDao: FavoriteTvShowDao
) : @Named("tvRepo") FavoriteTvShowRepository {
    override suspend fun insertFavourite(tvShow: FavoriteTvShow) {
        favoriteTvShowDao.insertFavorite(tvShow)
    }

    override fun getAllData(): LiveData<PagedList<FavoriteTvShow>> {
        val config =PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(50)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(favoriteTvShowDao.getAllData(), config).build()
    }

    override suspend fun isFavourite(idTvShow: Int): FavoriteTvShow =
        favoriteTvShowDao.isFavorite(idTvShow)

    override suspend fun deleteFavourite(tvShow: FavoriteTvShow) =
        favoriteTvShowDao.deleteFavorite(tvShow)
}