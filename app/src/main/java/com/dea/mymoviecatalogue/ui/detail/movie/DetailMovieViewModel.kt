package com.dea.mymoviecatalogue.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val movieDetail = MutableLiveData<DetailMovieResponse>()

    fun getMovie(movieId: Int) : MutableLiveData<DetailMovieResponse> {
        viewModelScope.launch {
            when (val response =  repository.getMovieById(movieId) ) {
                is Resource.Success -> {
                    response.data?.let {
                        movieDetail.postValue(it)
                    }
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return movieDetail
    }
}