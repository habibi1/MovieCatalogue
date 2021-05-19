package com.project.moviecatalogue.ui.movie.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel

/**
 * A fragment representing a list of Items.
 */
class MovieFragment : Fragment() {

    lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movie = viewModel.getData()

            if (movie.isEmpty()) {
                fragmentMovieBinding.layoutDataKosong.visibility = View.VISIBLE
                fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
                fragmentMovieBinding.rvMovie.visibility = View.INVISIBLE
            } else {
                fragmentMovieBinding.layoutDataKosong.visibility = View.INVISIBLE
                fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
                fragmentMovieBinding.rvMovie.visibility = View.VISIBLE

                val movieAdapter = MovieAdapter()
                movieAdapter.setMovies(movie)
                with(fragmentMovieBinding.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }
        }
    }
}