package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.TvShowEntity
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.project.moviecatalogue.utils.DataDummy

class TvShowViewModel(val catalogRepository: CatalogRepository): ViewModel() {
    fun getData(): List<TvShowEntity> = DataDummy.generateDummyTvShow()

    fun getDetail(index: Int): TvShowEntity {
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.addAll(DataDummy.generateDummyTvShow())
        return tvShow[index]
    }

    fun loadPopularTvShow(): LiveData<List<DetailTvShow>> = catalogRepository.getPapularTvShow()

    fun loadDetailTvShow(id: Int): LiveData<DetailTvShowResponse> = catalogRepository.getDetailTvShow(id)
}