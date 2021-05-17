package com.dea.mymoviecatalogue.data.api

import com.dea.mymoviecatalogue.BuildConfig
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.data.response.MovieResponse
import com.dea.mymoviecatalogue.data.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieResponse>

    @GET("tv/popular")
    suspend fun getPopularShows(
        @Query("api_key") apiKey: String
    ): Response<TvShowResponse>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirShows(
        @Query("api_key") apiKey: String
    ): Response<TvShowResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedShows(
        @Query("api_key") apiKey: String
    ): Response<TvShowResponse>

    @GET("tv/{tv_id}")
    suspend fun getTvShowById(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailTvShowResponse>
}
