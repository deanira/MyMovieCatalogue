package com.dea.mymoviecatalogue.ui.detail.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.repo.repointerface.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val tvShowDetail = MutableLiveData<DetailTvShowResponse>()

    fun getTvShow(tvShowId: Int) : MutableLiveData<DetailTvShowResponse>{
        viewModelScope.launch {
            when (val response = repository.getTvShowById(tvShowId) ) {
                is Resource.Success -> {
                    response.data?.let {
                        tvShowDetail.postValue(it)
                    }
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return tvShowDetail
    }
}