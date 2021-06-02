package com.project.moviecatalogue.ui.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.api.ApiConfig
import com.project.moviecatalogue.data.source.remote.response.DetailMovie
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.PopularMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(val catalogRepository: CatalogRepository): ViewModel() {

    fun loadPopularMovie(): LiveData<List<ListMovieEntity>> = catalogRepository.getPapularMovies()

    fun loadDetailMovie(id: Int): LiveData<DetailMovieEntity> = catalogRepository.getDetailMovie(id)
}