package com.dea.mymoviecatalogue.repo.repointerface

import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.data.response.MovieResponse
import com.dea.mymoviecatalogue.data.response.TvShowResponse

interface Repository {
    suspend fun getPopularMovies(): Resource<MovieResponse>
    suspend fun getNowPlayingMovies(): Resource<MovieResponse>
    suspend fun getTopRatedMovies(): Resource<MovieResponse>
    suspend fun getMovieById(id: Int): Resource<DetailMovieResponse>
    suspend fun getPopularShows(): Resource<TvShowResponse>
    suspend fun getOnTheAirShows(): Resource<TvShowResponse>
    suspend fun getTopRatedShows(): Resource<TvShowResponse>
    suspend fun getTvShowById(id: Int): Resource<DetailTvShowResponse>
}