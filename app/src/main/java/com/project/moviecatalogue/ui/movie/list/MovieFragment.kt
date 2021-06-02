package com.project.moviecatalogue.ui.movie.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    companion object{
        private const val TAG = "MovieFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            fragmentMovieBinding.layoutDataKosong.visibility = View.GONE
            fragmentMovieBinding.rvMovie.visibility = View.GONE

            val factory = ViewModelFactory.getInstance()
            val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieViewModel.loadPopularMovie().observe(viewLifecycleOwner, { listMovie ->

                if (listMovie == null) {
                    Log.i(TAG, "onChange: null")
                    fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                    fragmentMovieBinding.layoutDataKosong.visibility = View.GONE
                    fragmentMovieBinding.rvMovie.visibility = View.GONE
                } else {
                    if (listMovie.isEmpty()) {
                        Log.i(TAG, "onChange: empty")
                        fragmentMovieBinding.layoutDataKosong.visibility = View.VISIBLE
                        fragmentMovieBinding.progressBar.visibility = View.GONE
                        fragmentMovieBinding.rvMovie.visibility = View.GONE
                    } else {
                        Log.i(TAG, "onChange: not empty")
                        fragmentMovieBinding.layoutDataKosong.visibility = View.GONE
                        fragmentMovieBinding.progressBar.visibility = View.GONE
                        fragmentMovieBinding.rvMovie.visibility = View.VISIBLE

                        val movieAdapter = MovieAdapter()
                        movieAdapter.setMovies(listMovie)
                        with(fragmentMovieBinding.rvMovie) {
                            layoutManager = LinearLayoutManager(context)
                            setHasFixedSize(true)
                            adapter = movieAdapter
                        }
                    }
                }

                val movieAdapter = MovieAdapter()
                movieAdapter.setMovies(listMovie)
            })
        }
    }
}