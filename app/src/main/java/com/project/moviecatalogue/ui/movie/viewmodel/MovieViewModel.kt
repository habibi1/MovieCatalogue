package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity

class MovieViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    fun loadPopularMovie(): LiveData<List<ListMovieEntity>> =
        catalogRepository.getPapularMovies()

    fun loadDetailMovie(id: Int): LiveData<DetailMovieEntity> =
        catalogRepository.getDetailMovie(id)

    fun setFavoriteMovie(detailFavoriteMovie: DetailMovieEntity) {
        catalogRepository.setFavoriteMovie(detailFavoriteMovie)
    }

    fun getFavoriteMovieById(id: Int): LiveData<DetailMovieEntity> =
        catalogRepository.getFavoriteMovieById(id)

    fun deleteFavoriteMovie(detailFavoriteMovie: DetailMovieEntity) =
        catalogRepository.deleteFavoriteMovie(detailFavoriteMovie)
}