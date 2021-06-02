package com.project.moviecatalogue.ui.tvshow.list

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.moviecatalogue.BuildConfig
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.data.source.remote.response.DetailTvShow
import com.project.moviecatalogue.databinding.ItemMovieBinding
import com.project.moviecatalogue.ui.tvshow.detail.DetailTvShowActivity


class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var listTvShows = ArrayList<ListTvShowEntity>()

    fun setTvShows(tvShows: List<ListTvShowEntity>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
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
        val item = listTvShows[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(tvShows: ListTvShowEntity) {
            with(binding) {
                tvTitle.text = tvShows.name
                ratingBar.rating = tvShows.voteAverage.toFloat()/2
                tvRating.text = tvShows.voteAverage.toString()
                tvPemilih.text = tvShows.voteCount.toString() + " Pemilih"

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_DATA, tvShows.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_URL_IMAGE + tvShows.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(ivPoster)
            }
        }
    }
}