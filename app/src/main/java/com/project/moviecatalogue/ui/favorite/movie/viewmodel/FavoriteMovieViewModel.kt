package com.project.moviecatalogue.ui.favorite.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity

class FavoriteMovieViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<DetailMovieEntity>> =
        catalogRepository.getListFavoriteMovies()
}