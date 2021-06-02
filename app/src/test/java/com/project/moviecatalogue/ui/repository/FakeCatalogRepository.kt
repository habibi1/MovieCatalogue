package com.project.moviecatalogue.ui.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.moviecatalogue.data.source.CatalogDataSource
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.repository.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeCatalogRepository(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {

    override fun getPapularMovies(): LiveData<List<ListMovieEntity>> {
        val listMovieResult = MutableLiveData<List<ListMovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback{
                override fun onAllMoviesReceived(listMovieEntity: List<ListMovieEntity>) {
                    val movieList = ArrayList<ListMovieEntity>()
                    for (response in listMovieEntity){
                        movieList.add(response)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getDetailMovie(id: Int): LiveData<DetailMovieEntity> {
        val detailMovieResult = MutableLiveData<DetailMovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailMovie(id , object : RemoteDataSource.LoadDetailMovieCallback{
                override fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity) {
                    detailMovieResult.postValue(detailMovieEntity)
                }
            })
        }

        return detailMovieResult
    }

    override fun getPapularTvShow(): LiveData<List<ListTvShowEntity>> {
        val listTvShowResult = MutableLiveData<List<ListTvShowEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTvSHow(object : RemoteDataSource.LoadPopularTvShowCallback{
                override fun onAllTvShowReceived(listTvShowEntity: List<ListTvShowEntity>) {
                    val tvShowList = ArrayList<ListTvShowEntity>()
                    for (response in listTvShowEntity){
                        tvShowList.add(response)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity> {
        val detailTvShowResult = MutableLiveData<DetailTvShowEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailTvShow(id , object : RemoteDataSource.LoadDetailTvShowCallback{
                override fun onDetailTvShowReceived(detailTvShowEntity: DetailTvShowEntity) {
                    detailTvShowResult.postValue(detailTvShowEntity)
                }
            })
        }

        return detailTvShowResult
    }

}