package com.project.moviecatalogue.ui.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DetailMovieActivity"
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        val extras: Int = intent.getIntExtra(EXTRA_DATA, -1)

        val factory = ViewModelFactory.getInstance()
        val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieViewModel.loadDetailMovie(extras).observe(this, { data ->
            if (data == null) {
                Log.i(TAG, "onChange: null")
            } else {
                Log.i(TAG, "onChange: not null")

                activityDetailMovieBinding.apply {
                    tvTitleMovie.text = data.title
                    edtDurasi.setText(data.runtime.toString() + " Menit")
                    edtRilis.setText(data.releaseDate)
                    edtStatus.setText(data.status)
                    tvRating.text = data.voteAverage.toString()
                    tvPopularitas.text = data.popularity.toString()
                    tvVote.text = data.voteCount.toString()
                    tvBahasa.text = data.originalLanguage
                    tvDeskripsi.text = data.overview

                    Glide.with(this@DetailMovieActivity)
                        .load(BuildConfig.BASE_URL_IMAGE + data.posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivPoster)

                    Glide.with(this@DetailMovieActivity)
                        .load(BuildConfig.BASE_URL_IMAGE + data.backdropPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivBanner)
                }
            }
        })
    }
}