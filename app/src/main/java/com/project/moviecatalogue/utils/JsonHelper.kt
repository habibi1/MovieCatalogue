package com.project.moviecatalogue.utils

import android.content.Context
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadPopularMovies(): List<ListMovieEntity> {
        val list = ArrayList<ListMovieEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("PopularMovies.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val movieItem = ListMovieEntity(
                    movie.getInt("id"),
                    movie.getString("title"),
                    movie.getDouble("vote_average"),
                    movie.getInt("vote_count"),
                    movie.getString("poster_path")
                )
                list.add(movieItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailMovie(): DetailMovieEntity {
        val responseObject = JSONObject(parsingFileToString("DetailMovie.json").toString())
        return DetailMovieEntity(
            responseObject.getInt("id"),
            responseObject.getString("title"),
            responseObject.getInt("runtime"),
            responseObject.getString("release_date"),
            responseObject.getString("status"),
            responseObject.getDouble("vote_average"),
            responseObject.getDouble("popularity"),
            responseObject.getInt("vote_count"),
            responseObject.getString("original_language"),
            responseObject.getString("overview"),
            responseObject.getString("poster_path"),
            responseObject.getString("backdrop_path")
        )
    }

    fun loadPopularTvShows(): List<ListTvShowEntity> {
        val list = ArrayList<ListTvShowEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("PopularTvShows.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val tvShows = ListTvShowEntity(
                    tvShow.getInt("id"),
                    tvShow.getString("name"),
                    tvShow.getDouble("vote_average"),
                    tvShow.getInt("vote_count"),
                    tvShow.getString("poster_path")
                )
                list.add(tvShows)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailTvShow(): DetailTvShowEntity {
        val responseObject = JSONObject(parsingFileToString("DetailTvShow.json").toString())
        return DetailTvShowEntity(
            responseObject.getInt("id"),
            responseObject.getString("name"),
            responseObject.getString("status"),
            responseObject.getString("first_air_date"),
            responseObject.getInt("number_of_episodes"),
            responseObject.getDouble("vote_average"),
            responseObject.getDouble("popularity"),
            responseObject.getInt("vote_count"),
            responseObject.getString("original_language"),
            responseObject.getString("overview"),
            responseObject.getString("poster_path"),
            responseObject.getString("backdrop_path")
        )
    }

}