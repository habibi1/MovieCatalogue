package com.project.moviecatalogue.ui.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.utils.DataDummy

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        //setSupportActionBar(activityDetailBinding.toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val detailId = extras.getInt(EXTRA_DATA)
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val data = viewModel.getData()
            activityDetailMovieBinding.tvTitle.text = data[detailId].name
            activityDetailMovieBinding.edtGenre.setText(data[detailId].genreIds)
            activityDetailMovieBinding.edtDurasi.setText(data[detailId].genreIds)
            activityDetailMovieBinding.edtRilis.setText(data[detailId].firstAirDate)
            activityDetailMovieBinding.edtUsia.setText(data[detailId].genreIds)
            activityDetailMovieBinding.tvRating.text = data[detailId].voteAverage.toString()
            activityDetailMovieBinding.tvPopularitas.text = data[detailId].popularity.toString()
            activityDetailMovieBinding.tvVote.text = data[detailId].voteCount.toString()
            activityDetailMovieBinding.tvDeskripsi.text = data[detailId].overview

            Glide.with(this)
                .load(data[detailId].posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(activityDetailMovieBinding.ivPoster)
        }
    }
}