package com.project.moviecatalogue.ui.favorite.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity

class FavoriteTvShowViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<PagedList<DetailTvShowEntity>> =
        catalogRepository.getListFavoriteTvShow()
}