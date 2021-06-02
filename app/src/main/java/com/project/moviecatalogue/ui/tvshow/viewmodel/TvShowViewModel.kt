package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.TvShowEntity
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.project.moviecatalogue.utils.DataDummy

class TvShowViewModel(val catalogRepository: CatalogRepository): ViewModel() {

    fun loadPopularTvShow(): LiveData<List<ListTvShowEntity>> = catalogRepository.getPapularTvShow()

    fun loadDetailTvShow(id: Int): LiveData<DetailTvShowEntity> = catalogRepository.getDetailTvShow(id)
}