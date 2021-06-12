package com.project.moviecatalogue.ui.movie.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var detailMovieEntity: DetailMovieEntity
    private var extras: Int = 0
    private var statusFavorite: Boolean = false

    companion object {
        private const val TAG = "DetailMovieActivity"
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        extras = intent.getIntExtra(EXTRA_DATA, -1)

        val factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieViewModel.loadDetailMovie(extras).observe(this, { data ->
            if (data == null) {
                Log.i(TAG, "onChange: null")
            } else {
                Log.i(TAG, "onChange: not null")

                detailMovieEntity = data

                activityDetailMovieBinding.apply {
                    tvTitleMovie.text = data.title
                    edtDurasi.setText(data.runtime.toString() + getString(R.string.menit))
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
                                .error(R.drawable.ic_error)
                        )
                        .into(ivBanner)
                }
            }
        })

        movieViewModel.getFavoriteMovieById(extras).observe(this, { data ->
            statusFavorite = data != null
            setFavoriteState(statusFavorite)
        })

        activityDetailMovieBinding.apply {
            topAppBar.setNavigationOnClickListener {
                finish()
            }

            topAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite -> {
                        if (statusFavorite) {
                            movieViewModel.deleteFavoriteMovie(detailMovieEntity)
                            showSnackBar(getString(R.string.data_dihapus))
                        } else {
                            movieViewModel.setFavoriteMovie(detailMovieEntity)
                            showSnackBar(getString(R.string.data_disimpan))
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            activityDetailMovieBinding.topAppBar.menu.findItem(R.id.favorite)
                .setIcon(R.drawable.ic_save)
        } else {
            activityDetailMovieBinding.topAppBar.menu.findItem(R.id.favorite)
                .setIcon(R.drawable.ic_save_cancel)
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            findViewById(R.id.view_parent),
            message, Snackbar.LENGTH_SHORT
        )
            .show()
    }
}