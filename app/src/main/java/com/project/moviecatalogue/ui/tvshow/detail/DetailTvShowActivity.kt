package com.project.moviecatalogue.ui.tvshow.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DetailTvShowActivity"
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)

        val extras: Int = intent.getIntExtra(EXTRA_DATA, -1)

        val factory = ViewModelFactory.getInstance()
        val movieViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        movieViewModel.loadDetailTvShow(extras).observe(this, { data ->
            if (data == null) {
                Log.i(TAG, "onChange: null")
            } else {
                Log.i(TAG, "onChange: not null")

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
                                .error(R.drawable.ic_error))
                        .into(ivBanner)
                }
            }
        })
    }
}