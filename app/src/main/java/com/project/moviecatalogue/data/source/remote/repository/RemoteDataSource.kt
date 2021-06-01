package com.project.moviecatalogue.data.source.remote.repository

import android.util.Log
import com.project.moviecatalogue.api.ApiConfig
import com.project.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getPopularMovies(
        callback: LoadPopularMoviesCallback
    ) {
        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onAllMoviesReceived(
                            it.results
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailMovie(
        id: Int,
        callback: LoadDetailMovieCallback
    ) {
        val client = ApiConfig.getApiService().getDetailMovie(id)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onDetailMovieReceived(
                            it
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularTvSHow(
        callback: LoadPopularTvShowCallback
    ) {
        val client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onAllTvShowReceived(
                            it.results
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailTvShow(
        id: Int,
        callback: LoadDetailTvShowCallback
    ) {
        val client = ApiConfig.getApiService().getDetailTvShow(id)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onDetailTvShowReceived(
                            it
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadPopularMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<DetailMovie>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse)
    }

    interface LoadPopularTvShowCallback {
        fun onAllTvShowReceived(detailTvShow: List<DetailTvShow>)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShowResponse: DetailTvShowResponse)
    }
}