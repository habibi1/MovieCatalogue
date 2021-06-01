package com.project.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.moviecatalogue.data.source.remote.repository.RemoteDataSource
import com.project.moviecatalogue.data.source.remote.response.DetailMovie
import com.project.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.data.source.remote.response.DetailTvShowResponse
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

    override fun getPapularMovies(): LiveData<List<DetailMovie>> {
        val listMovieResult = MutableLiveData<List<DetailMovie>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<DetailMovie>) {
                    val movieList = ArrayList<DetailMovie>()
                    for (response in movieResponse){
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

    override fun getDetailMovie(id: Int): LiveData<DetailMovieResponse> {
        val detailMovieResult = MutableLiveData<DetailMovieResponse>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(id , object : RemoteDataSource.LoadDetailMovieCallback{
                override fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse) {
                    detailMovieResult.postValue(detailMovieResponse)
                }
            })
        }

        Log.i(TAG, "onReturn: $detailMovieResult")
        return detailMovieResult
    }

    override fun getPapularTvShow(): LiveData<List<DetailTvShow>> {
        val listTvShowResult = MutableLiveData<List<DetailTvShow>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTvSHow(object : RemoteDataSource.LoadPopularTvShowCallback{
                override fun onAllTvShowReceived(detailTvShow: List<DetailTvShow>) {
                    val tvShowList = ArrayList<DetailTvShow>()
                    for (response in detailTvShow){
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

    override fun getDetailTvShow(id: Int): LiveData<DetailTvShowResponse> {
        val detailTvShowResult = MutableLiveData<DetailTvShowResponse>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTvShow(id , object : RemoteDataSource.LoadDetailTvShowCallback{
                override fun onDetailTvShowReceived(detailTvShowResponse: DetailTvShowResponse) {
                    detailTvShowResult.postValue(detailTvShowResponse)
                }
            })
        }

        Log.i(TAG, "onReturn: $detailTvShowResult")
        return detailTvShowResult
    }
}