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

            activityDetailTvShowBinding.apply {
                tvTitleTvShow.text = data.name
                edtGenre.setText(data.genreIds)
                edtDurasi.setText(data.durasi)
                edtRilis.setText(data.firstAirDate)
                edtJumlahEpisode.setText(data.jumlahEpisode)
                tvRating.text = data.voteAverage.toString()
                tvPopularitas.text = data.popularity.toString()
                tvVote.text = data.voteCount.toString()
                tvBahasa.text = data.originalLanguage
                tvDeskripsi.text = data.overview

                Glide.with(this@DetailTvShowActivity)
                    .load(data.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivPoster)
            }
        }
    }
}