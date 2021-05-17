package com.dea.mymoviecatalogue.ui.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.TvShowResultsItem
import com.dea.mymoviecatalogue.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    val listPopularTvShows = MutableLiveData<ArrayList<TvShowResultsItem>>()
    val listOnTheAirTvShows = MutableLiveData<ArrayList<TvShowResultsItem>>()
    val listTopRatedTvShows = MutableLiveData<ArrayList<TvShowResultsItem>>()

    fun getPopularTvShows() : MutableLiveData<ArrayList<TvShowResultsItem>> {
        viewModelScope.launch {
            val tvShowsResponse: ArrayList<TvShowResultsItem> = arrayListOf()
            when (val response = repository.getPopularShows()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        tvShowsResponse.add(it)
                    }
                    listPopularTvShows.postValue(tvShowsResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listPopularTvShows
    }

    fun getOnTheAirTvShows() : MutableLiveData<ArrayList<TvShowResultsItem>> {
        viewModelScope.launch {
            val tvShowsResponse: ArrayList<TvShowResultsItem> = arrayListOf()
            when (val response = repository.getOnTheAirShows()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        tvShowsResponse.add(it)
                    }
                    listOnTheAirTvShows.postValue(tvShowsResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listOnTheAirTvShows
    }

    fun getTopRatedTvShows() : MutableLiveData<ArrayList<TvShowResultsItem>> {
        viewModelScope.launch {
            val tvShowsResponse: ArrayList<TvShowResultsItem> = arrayListOf()
            when (val response = repository.getTopRatedShows()) {
                is Resource.Success -> {
                    response.data?.results?.forEach {
                        tvShowsResponse.add(it)
                    }
                    listTopRatedTvShows.postValue(tvShowsResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listTopRatedTvShows
    }
}