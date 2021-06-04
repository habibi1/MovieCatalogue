package com.project.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity

interface CatalogDataSource {
    fun getPapularMovies(): LiveData<List<ListMovieEntity>>
    fun getDetailMovie(id: Int): LiveData<DetailMovieEntity>
    fun getPapularTvShow(): LiveData<List<ListTvShowEntity>>
    fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity>
}