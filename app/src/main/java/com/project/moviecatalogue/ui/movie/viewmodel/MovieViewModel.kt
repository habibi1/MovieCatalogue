package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.project.moviecatalogue.data.MovieEntity
import com.project.moviecatalogue.utils.DataDummy

class MovieViewModel: ViewModel() {
    fun getData(): List<MovieEntity> = DataDummy.generateDummyMovie()
}