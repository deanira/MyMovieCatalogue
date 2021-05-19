package com.dea.mymoviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.repo.repointerface.FavoriteMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    @Named("movieRepo") private val favoriteMovieRepository: FavoriteMovieRepository
) : ViewModel() {
    val isFav = MutableLiveData<FavoriteMovie>()

    private val _insertFavoriteMovieStatus = MutableLiveData<Resource<FavoriteMovie>>()
    val insertFavoriteMovieStatus: LiveData<Resource<FavoriteMovie>> = _insertFavoriteMovieStatus

    private val _deleteFavoriteMovieStatus = MutableLiveData<Resource<FavoriteMovie>>()
    val deleteFavoriteMovieStatus: LiveData<Resource<FavoriteMovie>> = _deleteFavoriteMovieStatus

    fun getFavoriteMovies(): LiveData<PagedList<FavoriteMovie>> =
        favoriteMovieRepository.getAllData()

    fun isFavoriteMovie(id: Int) = viewModelScope.launch {
        isFav.postValue(favoriteMovieRepository.isFavourite(id))
    }

    fun deleteFromFavorite(item: FavoriteMovie) = viewModelScope.launch {
        favoriteMovieRepository.deleteFavourite(item)
        _deleteFavoriteMovieStatus.postValue(Resource.Success(item, "Item removed from favorite"))
    }

    fun insertToFavorite(item: FavoriteMovie) = viewModelScope.launch {
        favoriteMovieRepository.insertFavourite(item)
        _insertFavoriteMovieStatus.postValue(Resource.Success(item, "Item added to favorite"))
    }
}