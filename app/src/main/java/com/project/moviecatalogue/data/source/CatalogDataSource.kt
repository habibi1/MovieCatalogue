package com.project.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.response.DetailMovie
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse

interface CatalogDataSource {
    fun getPapularMovies(): LiveData<List<ListMovieEntity>>
    fun getDetailMovie(id: Int): LiveData<DetailMovieEntity>
    fun getPapularTvShow(): LiveData<List<ListTvShowEntity>>
    fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity>
}