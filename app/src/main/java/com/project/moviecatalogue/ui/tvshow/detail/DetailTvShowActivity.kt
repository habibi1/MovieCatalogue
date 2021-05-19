package com.project.moviecatalogue.ui.tvshow.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.project.moviecatalogue.ui.movie.detail.DetailMovieActivity
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel
import com.project.moviecatalogue.utils.DataDummy

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)

        //setSupportActionBar(ActivityDetailTvShowBinding.toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val detailId = extras.getInt(DetailMovieActivity.EXTRA_DATA)
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val data = viewModel.getData()
            activityDetailTvShowBinding.tvTitle.text = data[detailId].name
            activityDetailTvShowBinding.edtGenre.setText(data[detailId].genreIds)
            activityDetailTvShowBinding.edtDurasi.setText(data[detailId].genreIds)
            activityDetailTvShowBinding.edtRilis.setText(data[detailId].firstAirDate)
            activityDetailTvShowBinding.edtUsia.setText(data[detailId].genreIds)
            activityDetailTvShowBinding.tvRating.text = data[detailId].voteAverage.toString()
            activityDetailTvShowBinding.tvPopularitas.text = data[detailId].popularity.toString()
            activityDetailTvShowBinding.tvVote.text = data[detailId].voteCount.toString()
            activityDetailTvShowBinding.tvDeskripsi.text = data[detailId].overview

            Glide.with(this)
                .load(data[detailId].posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(activityDetailTvShowBinding.ivPoster)
        }
    }
}