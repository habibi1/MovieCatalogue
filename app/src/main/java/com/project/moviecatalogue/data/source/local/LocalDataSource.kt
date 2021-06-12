package com.project.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getListFavoriteMovies(): DataSource.Factory<Int, DetailMovieEntity> =
        catalogueDao.getAllFavoriteMovie()

    fun insertFavoriteMovie(detailFavoriteMovie: DetailMovieEntity) {
        catalogueDao.inserFavoriteMovie(detailFavoriteMovie)
    }

    fun getDetailMovie(id: Int): LiveData<DetailMovieEntity> =
        catalogueDao.getDetailMovieById(id)

    fun deleteFavoriteMovie(detailFavoriteMovie: DetailMovieEntity) =
        catalogueDao.deleteFavoriteMovie(detailFavoriteMovie)

    fun getListFavoriteTvShow(): DataSource.Factory<Int, DetailTvShowEntity> =
        catalogueDao.getAllFavoriteTvShow()

    fun insertFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity) {
        catalogueDao.inserFavoriteTvShow(detailTvShowEntity)
    }

    fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity> =
        catalogueDao.getDetailTvShowById(id)

    fun deleteFavoriteTvShow(detailTvShowEntity: DetailTvShowEntity) =
        catalogueDao.deleteFavoriteTvSHow(detailTvShowEntity)
}