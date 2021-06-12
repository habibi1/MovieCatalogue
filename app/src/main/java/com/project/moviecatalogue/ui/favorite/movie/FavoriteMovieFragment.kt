package com.project.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.project.moviecatalogue.ui.favorite.movie.viewmodel.FavoriteMovieViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private lateinit var fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding

    companion object {
        private const val TAG = "FavoriteMovieFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            loadData()

            val factory = ViewModelFactory.getInstance(requireContext())
            val movieViewModel =
                ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            movieViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { listMovie ->
                if (listMovie.isEmpty()) {
                    Log.i(TAG, "onChange: empty")
                    dataEmpty()
                } else {
                    Log.i(TAG, "onChange: not empty")
                    dataLoaded()

                    val favoriteMovieAdapter = FavoriteMovieAdapter()
                    favoriteMovieAdapter.submitList(listMovie)
                    with(fragmentFavoriteMovieBinding.rvFavoriteMovie) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = favoriteMovieAdapter
                    }
                }
            })
        }
    }

    private fun loadData() {
        fragmentFavoriteMovieBinding.apply {
            progressBar.visibility = View.VISIBLE
            layoutDataKosongFavoriteMovie.visibility = View.GONE
            rvFavoriteMovie.visibility = View.GONE
        }
    }

    private fun dataEmpty() {
        fragmentFavoriteMovieBinding.apply {
            layoutDataKosongFavoriteMovie.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            rvFavoriteMovie.visibility = View.GONE
        }
    }

    private fun dataLoaded() {
        fragmentFavoriteMovieBinding.apply {
            layoutDataKosongFavoriteMovie.visibility = View.GONE
            progressBar.visibility = View.GONE
            rvFavoriteMovie.visibility = View.VISIBLE
        }
    }
}