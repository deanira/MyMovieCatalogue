package com.dea.mymoviecatalogue.repo

import com.dea.mymoviecatalogue.BuildConfig
import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.api.ApiService
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.data.response.MovieResponse
import com.dea.mymoviecatalogue.data.response.TvShowResponse
import com.dea.mymoviecatalogue.utils.EspressoIdlingResource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : Repository{

    override suspend fun getPopularMovies(): Resource<MovieResponse> {
        EspressoIdlingResource.increment()
        apiService.getPopularMovies(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getNowPlayingMovies(): Resource<MovieResponse> {
        EspressoIdlingResource.increment()
        apiService.getNowPlayingMovies(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getTopRatedMovies(): Resource<MovieResponse> {
        EspressoIdlingResource.increment()
        apiService.getTopRatedMovies(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getMovieById(id: Int): Resource<DetailMovieResponse> {
        EspressoIdlingResource.increment()
        apiService.getMovieById(
            id,
            BuildConfig.API_KEY
        )
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getPopularShows(): Resource<TvShowResponse> {
        EspressoIdlingResource.increment()
        apiService.getPopularShows(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getOnTheAirShows(): Resource<TvShowResponse> {
        EspressoIdlingResource.increment()
        apiService.getOnTheAirShows(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getTopRatedShows(): Resource<TvShowResponse> {
        EspressoIdlingResource.increment()
        apiService.getTopRatedShows(BuildConfig.API_KEY)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }

    override suspend fun getTvShowById(id: Int): Resource<DetailTvShowResponse> {
        EspressoIdlingResource.increment()
        apiService.getTvShowById(
            id,
            BuildConfig.API_KEY
        )
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        EspressoIdlingResource.decrement()
                        return Resource.Success(it)
                    }
                }
                EspressoIdlingResource.decrement()
                return Resource.Error(response.message())
            }
    }
}