package com.dea.mymoviecatalogue.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.MovieResultsItem
import com.dea.mymoviecatalogue.repo.MainRepository
import com.dea.mymoviecatalogue.repo.repointerface.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val listPopularMovies = MutableLiveData<ArrayList<MovieResultsItem>>()
    val listNowPlayingMovies = MutableLiveData<ArrayList<MovieResultsItem>>()
    val listTopRatedMovies = MutableLiveData<ArrayList<MovieResultsItem>>()

    fun getPopularMovies() : MutableLiveData<ArrayList<MovieResultsItem>> {
        viewModelScope.launch {
            val moviesResponse: ArrayList<MovieResultsItem> = arrayListOf()
            when (val response = repository.getPopularMovies()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        moviesResponse.add(it)
                    }
                    listPopularMovies.postValue(moviesResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listPopularMovies
    }

    fun getNowPlayingMovies() : MutableLiveData<ArrayList<MovieResultsItem>> {
        viewModelScope.launch {
            val moviesResponse: ArrayList<MovieResultsItem> = arrayListOf()
            when (val response = repository.getNowPlayingMovies()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        moviesResponse.add(it)
                    }
                    listNowPlayingMovies.postValue(moviesResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listNowPlayingMovies
    }

    fun getTopRatedMovies() : MutableLiveData<ArrayList<MovieResultsItem>> {
        viewModelScope.launch {
            val moviesResponse: ArrayList<MovieResultsItem> = arrayListOf()
            when (val response = repository.getTopRatedMovies()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        moviesResponse.add(it)
                    }
                    listTopRatedMovies.postValue(moviesResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listTopRatedMovies
    }
}