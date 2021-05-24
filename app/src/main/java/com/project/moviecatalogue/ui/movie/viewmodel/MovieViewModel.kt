package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.MovieEntity
import com.project.moviecatalogue.utils.DataDummy

class MovieViewModel: ViewModel() {

    fun getData(): List<MovieEntity> = DataDummy.generateDummyMovie()

    fun getDetail(index: Int): MovieEntity {
        val movie = ArrayList<MovieEntity>()
        movie.addAll(DataDummy.generateDummyMovie())
        return movie[index]
    }
}