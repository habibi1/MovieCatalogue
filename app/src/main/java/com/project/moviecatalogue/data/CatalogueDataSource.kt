package com.project.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity

interface CatalogueDataSource {
    fun getPapularMovies(): LiveData<List<ListMovieEntity>>
    fun getDetailMovie(id: Int): LiveData<DetailMovieEntity>

    fun getPapularTvShow(): LiveData<List<ListTvShowEntity>>
    fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity>

    fun getListFavoriteMovies(): LiveData<PagedList<DetailMovieEntity>>
    fun setFavoriteMovie(detailMovieEntity: DetailMovieEntity)
    fun getFavoriteMovieById(id: Int): LiveData<DetailMovieEntity>
    fun deleteFavoriteMovie(detailMovieEntity: DetailMovieEntity)

    fun getListFavoriteTvShow(): LiveData<PagedList<DetailTvShowEntity>>
    fun setFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity)
    fun getFavoriteTvShowById(id: Int): LiveData<DetailTvShowEntity>
    fun deleteFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity)
}