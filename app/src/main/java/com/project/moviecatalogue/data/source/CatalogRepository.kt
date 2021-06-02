package com.project.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.repository.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {

    companion object {
        private const val TAG = "CatalogRepository"

        @Volatile
        private var instance: CatalogRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPapularMovies(): LiveData<List<ListMovieEntity>> {
        val listMovieResult = MutableLiveData<List<ListMovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback{
                override fun onAllMoviesReceived(listMovieEntity: List<ListMovieEntity>) {
                    val movieList = ArrayList<ListMovieEntity>()
                    for (response in listMovieEntity){
                        movieList.add(response)
                    }
                    Log.i(TAG, "onChange: $movieList")
                    listMovieResult.postValue(movieList)
                }
            })
        }

        Log.i(TAG, "onReturn: $listMovieResult")
        return listMovieResult
    }

    override fun getDetailMovie(id: Int): LiveData<DetailMovieEntity> {
        val detailMovieResult = MutableLiveData<DetailMovieEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(id , object : RemoteDataSource.LoadDetailMovieCallback{
                override fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity) {
                    detailMovieResult.postValue(detailMovieEntity)
                }
            })
        }

        Log.i(TAG, "onReturn: $detailMovieResult")
        return detailMovieResult
    }

    override fun getPapularTvShow(): LiveData<List<ListTvShowEntity>> {
        val listTvShowResult = MutableLiveData<List<ListTvShowEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTvSHow(object : RemoteDataSource.LoadPopularTvShowCallback{
                override fun onAllTvShowReceived(listTvShowEntity: List<ListTvShowEntity>) {
                    val tvShowList = ArrayList<ListTvShowEntity>()
                    for (response in listTvShowEntity){
                        tvShowList.add(response)
                    }
                    Log.i(TAG, "onChange: $tvShowList")
                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        Log.i(TAG, "onReturn: $listTvShowResult")
        return listTvShowResult
    }

    override fun getDetailTvShow(id: Int): LiveData<DetailTvShowEntity> {
        val detailTvShowResult = MutableLiveData<DetailTvShowEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTvShow(id , object : RemoteDataSource.LoadDetailTvShowCallback{
                override fun onDetailTvShowReceived(detailTvShowEntity: DetailTvShowEntity) {
                    detailTvShowResult.postValue(detailTvShowEntity)
                }
            })
        }

        Log.i(TAG, "onReturn: $detailTvShowResult")
        return detailTvShowResult
    }
}