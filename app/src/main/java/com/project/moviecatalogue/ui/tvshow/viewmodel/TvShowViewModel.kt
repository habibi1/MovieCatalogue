package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.TvShowEntity
import com.project.moviecatalogue.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getData(): List<TvShowEntity> = DataDummy.generateDummyTvShow()

    fun getDetail(index: Int): TvShowEntity {
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.addAll(DataDummy.generateDummyTvShow())
        return tvShow[index]
    }
}