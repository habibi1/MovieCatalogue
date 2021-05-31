package com.project.moviecatalogue.api

import com.project.moviecatalogue.data.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Call<PopularMovieResponse>

    @GET("/movie/popular/{id}")
    fun getDetailMovie(
        @Path("id") id: String
    ): Call<PopularMovieResponse>

}