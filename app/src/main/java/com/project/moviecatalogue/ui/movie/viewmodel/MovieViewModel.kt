package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity

class MovieViewModel(val catalogRepository: CatalogRepository): ViewModel() {

    fun loadPopularMovie(): LiveData<List<ListMovieEntity>> = catalogRepository.getPapularMovies()

    fun loadDetailMovie(id: Int): LiveData<DetailMovieEntity> = catalogRepository.getDetailMovie(id)
}