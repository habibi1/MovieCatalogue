package com.project.moviecatalogue.api

import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.project.moviecatalogue.data.source.remote.response.PopularMovieResponse
import com.project.moviecatalogue.data.source.remote.response.PopularTvShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<PopularMovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<PopularTvShowResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<DetailTvShowResponse>

}