package com.project.moviecatalogue.ui.tvshow.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)

        val extras = intent.extras
        if (extras != null) {
            val detailId = extras.getInt(EXTRA_DATA)
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val data = viewModel.getDetail(detailId)
            activityDetailTvShowBinding.tvTitleTvShow.text = data.name
            activityDetailTvShowBinding.edtGenre.setText(data.genreIds)
            activityDetailTvShowBinding.edtDurasi.setText(data.durasi)
            activityDetailTvShowBinding.edtRilis.setText(data.firstAirDate)
            activityDetailTvShowBinding.edtJumlahEpisode.setText(data.jumlahEpisode)
            activityDetailTvShowBinding.tvRating.text = data.voteAverage.toString()
            activityDetailTvShowBinding.tvPopularitas.text = data.popularity.toString()
            activityDetailTvShowBinding.tvVote.text = data.voteCount.toString()
            activityDetailTvShowBinding.tvBahasa.text = data.originalLanguage
            activityDetailTvShowBinding.tvDeskripsi.text = data.overview

            Glide.with(this)
                .load(data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(activityDetailTvShowBinding.ivPoster)
        }
    }
}