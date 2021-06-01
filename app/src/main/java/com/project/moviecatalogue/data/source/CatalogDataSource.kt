package com.project.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.project.moviecatalogue.data.source.remote.response.DetailMovie
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse

interface CatalogDataSource {
    fun getPapularMovies(): LiveData<List<DetailMovie>>
    fun getDetailMovie(id: Int): LiveData<DetailMovieResponse>
    fun getPapularTvShow(): LiveData<List<DetailTvShow>>
    fun getDetailTvShow(id: Int): LiveData<DetailTvShowResponse>
}