package com.project.moviecatalogue.ui.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.api.ApiConfig
import com.project.moviecatalogue.data.source.remote.response.DetailMovie
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.PopularMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(val catalogRepository: CatalogRepository): ViewModel() {

    private val _listMovie = MutableLiveData<List<DetailMovie>>()
    val listMovie: LiveData<List<DetailMovie>> = _listMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MovieViewModel"
    }

    init {
        //loadPopular()
        //loadPopularMovie()
    }

    fun loadPopular() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    //_listMovie.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun loadPopularMovie(): LiveData<List<DetailMovie>> = catalogRepository.getPapularMovies()

    fun loadDetailMovie(id: Int): LiveData<DetailMovieResponse> = catalogRepository.getDetailMovie(id)
}