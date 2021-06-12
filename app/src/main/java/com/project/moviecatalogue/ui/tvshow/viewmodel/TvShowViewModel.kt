package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity

class TvShowViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    fun loadPopularTvShow(): LiveData<List<ListTvShowEntity>> =
        catalogRepository.getPapularTvShow()

    fun loadDetailTvShow(id: Int): LiveData<DetailTvShowEntity> =
        catalogRepository.getDetailTvShow(id)

    fun setFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity) {
        catalogRepository.setFavoriteTvShow(detailTvShowEntity)
    }

    fun getFavoriteTvShowById(id: Int): LiveData<DetailTvShowEntity> =
        catalogRepository.getFavoriteTvShowById(id)

    fun deleteFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity) =
        catalogRepository.deleteFavoriteTvShow(detailTvShowEntity)
}