package com.project.moviecatalogue.ui.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.api.ApiConfig
import com.project.moviecatalogue.data.DetailMovie
import com.project.moviecatalogue.data.MovieEntity
import com.project.moviecatalogue.data.PopularMovieResponse
import com.project.moviecatalogue.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    private val _listMovie = MutableLiveData<List<DetailMovie>>()
    val listMovie: LiveData<List<DetailMovie>> = _listMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MovieViewModel"
        //private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        loadPopularMovie()
    }

    fun loadPopularMovie() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularMovie("9eda808b062dc5bb6e2123b231b8ba84")
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listMovie.value = response.body()?.results
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
}