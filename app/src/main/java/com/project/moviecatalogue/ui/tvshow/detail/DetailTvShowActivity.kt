package com.project.moviecatalogue.ui.tvshow.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var detailTvShowEntity: DetailTvShowEntity
    private var extras: Int = 0
    private var statusFavorite: Boolean = false

    companion object {
        private const val TAG = "DetailTvShowActivity"
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)

        extras = intent.getIntExtra(EXTRA_DATA, -1)

        val factory = ViewModelFactory.getInstance(this)
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowViewModel.loadDetailTvShow(extras).observe(this, { data ->
            if (data == null) {
                Log.i(TAG, "onChange: null")
            } else {
                Log.i(TAG, "onChange: not null")

                detailTvShowEntity = data

                activityDetailTvShowBinding.apply {
                    tvTitleTvShow.text = data.name
                    edtStatus.setText(data.status)
                    edtRilis.setText(data.firstAirDate)
                    edtJumlahEpisode.setText(data.numberOfEpisodes.toString())
                    tvRating.text = data.voteAverage.toString()
                    tvPopularitas.text = data.popularity.toString()
                    tvVote.text = data.voteCount.toString()
                    tvBahasa.text = data.originalLanguage
                    tvDeskripsi.text = data.overview

                    Glide.with(this@DetailTvShowActivity)
                        .load(BuildConfig.BASE_URL_IMAGE + data.posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivPoster)

                    Glide.with(this@DetailTvShowActivity)
                        .load(BuildConfig.BASE_URL_IMAGE + data.backdropPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(ivBanner)
                }
            }
        })

        tvShowViewModel.getFavoriteTvShowById(extras).observe(this, { data ->
            statusFavorite = data != null
            setFavoriteState(statusFavorite)
        })

        activityDetailTvShowBinding.apply {
            topAppBar.setNavigationOnClickListener {
                finish()
            }

            topAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite -> {
                        if (statusFavorite) {
                            tvShowViewModel.deleteFavoriteTvShow(detailTvShowEntity)
                            showSnackBar(getString(R.string.data_dihapus))
                        } else {
                            tvShowViewModel.setFavoriteTvShow(detailTvShowEntity)
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
            activityDetailTvShowBinding.topAppBar.menu.findItem(R.id.favorite)
                .setIcon(R.drawable.ic_save)
        } else {
            activityDetailTvShowBinding.topAppBar.menu.findItem(R.id.favorite)
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