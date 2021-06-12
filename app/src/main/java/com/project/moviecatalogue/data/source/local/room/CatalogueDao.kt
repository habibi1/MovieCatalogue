package com.project.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * from detail_favorite_movie")
    fun getAllFavoriteMovie(): DataSource.Factory<Int, DetailMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserFavoriteMovie(detailFavoriteMovie: DetailMovieEntity)

    @Query("SELECT * FROM detail_favorite_movie WHERE id = :id")
    fun getDetailMovieById(id: Int): LiveData<DetailMovieEntity>

    @Delete
    fun deleteFavoriteMovie(detailFavoriteMovie: DetailMovieEntity)

    @Query("SELECT * from detail_favorite_tv_show")
    fun getAllFavoriteTvShow(): DataSource.Factory<Int, DetailTvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserFavoriteTvShow(detailFavoriteTvShow: DetailTvShowEntity)

    @Query("SELECT * from detail_favorite_tv_show WHERE id = :id")
    fun getDetailTvShowById(id: Int): LiveData<DetailTvShowEntity>

    @Delete
    fun deleteFavoriteTvSHow(detailFavoriteTvShow: DetailTvShowEntity)
}