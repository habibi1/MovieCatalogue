package com.project.moviecatalogue.ui.movie.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.MovieEntity
import com.project.moviecatalogue.databinding.ItemMovieBinding
import com.project.moviecatalogue.ui.movie.detail.DetailMovieActivity

import com.project.moviecatalogue.ui.tvshow.detail.DetailTvShowActivity

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.name
                tvGenre.text = movie.genreIds
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivPoster)
            }
        }
    }

}