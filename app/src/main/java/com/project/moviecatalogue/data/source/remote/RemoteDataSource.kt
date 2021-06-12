package com.project.moviecatalogue.data.source.remote

import android.util.Log
import com.project.moviecatalogue.api.ApiConfig
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.project.moviecatalogue.data.source.remote.response.PopularMovieResponse
import com.project.moviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.project.moviecatalogue.utils.EspressoIdlingResource
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
        EspressoIdlingResource.increment()

        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                EspressoIdlingResource.decrement()

                if (response.isSuccessful) {
                    val movieList = ArrayList<ListMovieEntity>()
                    response.body()!!.results.forEach { data ->
                        val item = ListMovieEntity(
                            data.id,
                            data.title,
                            data.voteAverage,
                            data.voteCount,
                            data.posterPath
                        )
                        movieList.add(item)
                    }
                    callback.onAllMoviesReceived(movieList)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailMovie(
        id: Int,
        callback: LoadDetailMovieCallback
    ) {
        EspressoIdlingResource.increment()

        val client = ApiConfig.getApiService().getDetailMovie(id)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                EspressoIdlingResource.decrement()

                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onDetailMovieReceived(
                            DetailMovieEntity(
                                it.id,
                                it.title,
                                it.runtime,
                                it.releaseDate,
                                it.status,
                                it.voteAverage,
                                it.popularity,
                                it.voteCount,
                                it.originalLanguage,
                                it.overview,
                                it.posterPath,
                                it.backdropPath
                            )
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularTvSHow(
        callback: LoadPopularTvShowCallback
    ) {
        EspressoIdlingResource.increment()

        val client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                EspressoIdlingResource.decrement()

                if (response.isSuccessful) {
                    val tvShowList = ArrayList<ListTvShowEntity>()
                    response.body()!!.results.forEach { data ->
                        val item = ListTvShowEntity(
                            data.id,
                            data.name,
                            data.voteAverage,
                            data.voteCount,
                            data.posterPath
                        )
                        tvShowList.add(item)
                    }
                    callback.onAllTvShowReceived(tvShowList)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailTvShow(
        id: Int,
        callback: LoadDetailTvShowCallback
    ) {
        EspressoIdlingResource.increment()

        val client = ApiConfig.getApiService().getDetailTvShow(id)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                EspressoIdlingResource.decrement()

                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onDetailTvShowReceived(
                            DetailTvShowEntity(
                                it.id,
                                it.name,
                                it.status,
                                it.firstAirDate,
                                it.numberOfEpisodes,
                                it.voteAverage,
                                it.popularity,
                                it.voteCount,
                                it.originalLanguage,
                                it.overview,
                                it.posterPath,
                                it.backdropPath
                            )
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadPopularMoviesCallback {
        fun onAllMoviesReceived(listMovieEntity: List<ListMovieEntity>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity)
    }

    interface LoadPopularTvShowCallback {
        fun onAllTvShowReceived(listTvShowEntity: List<ListTvShowEntity>)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShowEntity: DetailTvShowEntity)
    }
}