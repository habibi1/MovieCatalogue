package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity

class TvShowViewModel(val catalogRepository: CatalogRepository): ViewModel() {

    fun loadPopularTvShow(): LiveData<List<ListTvShowEntity>> = catalogRepository.getPapularTvShow()

    fun loadDetailTvShow(id: Int): LiveData<DetailTvShowEntity> = catalogRepository.getDetailTvShow(id)
}