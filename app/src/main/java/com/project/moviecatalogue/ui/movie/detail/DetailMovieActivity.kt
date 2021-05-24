package com.project.moviecatalogue.ui.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        val extras = intent.extras
        if (extras != null) {
            val detailId = extras.getInt(EXTRA_DATA)
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val data = viewModel.getDetail(detailId)
            activityDetailMovieBinding.tvTitleMovie.text = data.name
            activityDetailMovieBinding.edtGenre.setText(data.genreIds)
            activityDetailMovieBinding.edtDurasi.setText(data.durasi)
            activityDetailMovieBinding.edtRilis.setText(data.firstAirDate)
            activityDetailMovieBinding.edtUsia.setText(data.usia)
            activityDetailMovieBinding.tvRating.text = data.voteAverage.toString()
            activityDetailMovieBinding.tvPopularitas.text = data.popularity.toString()
            activityDetailMovieBinding.tvVote.text = data.voteCount.toString()
            activityDetailMovieBinding.tvBahasa.text = data.originalLanguage
            activityDetailMovieBinding.tvDeskripsi.text = data.overview

            Glide.with(this)
                .load(data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(activityDetailMovieBinding.ivPoster)
        }
    }
}