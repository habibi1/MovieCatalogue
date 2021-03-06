package com.project.moviecatalogue.ui.movie.list

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.databinding.ItemMovieBinding
import com.project.moviecatalogue.ui.movie.detail.DetailMovieActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<ListMovieEntity>()
    private var allListMovies = ArrayList<ListMovieEntity>()

    fun setMovies(movies: List<ListMovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        this.allListMovies.clear()
        this.allListMovies.addAll(movies)
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

        @SuppressLint("SetTextI18n")
        fun bind(movie: ListMovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                ratingBar.rating = movie.voteAverage.toFloat() / 2
                tvRating.text = movie.voteAverage.toString()
                tvPemilih.text =
                    movie.voteCount.toString() + binding.root.resources.getString(R.string.pemilih)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_URL_IMAGE + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
            }
        }
    }

    fun sortData(status: Boolean) {
        if (status) {
            listMovies.sortWith(compareBy { it.title })
        } else {
            listMovies.clear()
            listMovies.addAll(allListMovies)
        }
        notifyDataSetChanged()
    }
}