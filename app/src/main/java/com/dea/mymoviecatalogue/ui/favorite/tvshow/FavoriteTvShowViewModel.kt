package com.dea.myTvShowcatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteTvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FavoriteTvShowViewModel @Inject constructor(
    @Named("tvRepo") private val favoriteTvShowRepository: FavoriteTvShowRepository
): ViewModel() {
    val isFav = MutableLiveData<FavoriteTvShow>()

    private val _insertFavoriteTvShowStatus = MutableLiveData<Resource<FavoriteTvShow>>()
    val insertFavoriteTvShowStatus: LiveData<Resource<FavoriteTvShow>> = _insertFavoriteTvShowStatus

    private val _deleteFavoriteTvShowStatus = MutableLiveData<Resource<FavoriteTvShow>>()
    val deleteFavoriteTvShowStatus: LiveData<Resource<FavoriteTvShow>> = _deleteFavoriteTvShowStatus

    fun getFavoriteTvShows(): LiveData<PagedList<FavoriteTvShow>> =
        favoriteTvShowRepository.getAllData()

    fun isFavoriteTvShow(id: Int) = viewModelScope.launch {
        isFav.postValue(favoriteTvShowRepository.isFavourite(id))
    }

    fun deleteFromFavorite(item: FavoriteTvShow) = viewModelScope.launch {
        favoriteTvShowRepository.deleteFavourite(item)
        _deleteFavoriteTvShowStatus.postValue(Resource.Success(item, "Item removed from favorite"))
    }

    fun insertToFavorite(item: FavoriteTvShow) = viewModelScope.launch {
        favoriteTvShowRepository.insertFavourite(item)
        _insertFavoriteTvShowStatus.postValue(Resource.Success(item, "Item added to favorite"))
    }
}