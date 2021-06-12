package com.project.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.project.moviecatalogue.ui.favorite.tvshow.viewmodel.FavoriteTvShowViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private lateinit var fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding

    companion object {
        private const val TAG = "FavoriteMovieFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return fragmentFavoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            loadData()

            val factory = ViewModelFactory.getInstance(requireContext())
            val tvShowViewModel =
                ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            tvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { listTvShow ->
                if (listTvShow.isEmpty()) {
                    Log.i(TAG, "onChange: empty")
                    dataEmpty()
                } else {
                    Log.i(TAG, "onChange: not empty")
                    dataLoaded()

                    val favoriteTvShowAdapter = FavoriteTvShowAdapter()
                    favoriteTvShowAdapter.submitList(listTvShow)
                    with(fragmentFavoriteTvShowBinding.rvFavoriteTvShow) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = favoriteTvShowAdapter
                    }
                }
            })
        }
    }

    private fun loadData() {
        fragmentFavoriteTvShowBinding.apply {
            progressBar.visibility = View.VISIBLE
            layoutDataKosongFavoriteTvShow.visibility = View.GONE
            rvFavoriteTvShow.visibility = View.GONE
        }
    }

    private fun dataEmpty() {
        fragmentFavoriteTvShowBinding.apply {
            layoutDataKosongFavoriteTvShow.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            rvFavoriteTvShow.visibility = View.GONE
        }
    }

    private fun dataLoaded() {
        fragmentFavoriteTvShowBinding.apply {
            layoutDataKosongFavoriteTvShow.visibility = View.GONE
            progressBar.visibility = View.GONE
            rvFavoriteTvShow.visibility = View.VISIBLE
        }
    }
}