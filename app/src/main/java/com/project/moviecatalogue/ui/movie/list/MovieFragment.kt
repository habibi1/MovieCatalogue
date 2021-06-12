package com.project.moviecatalogue.ui.movie.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.databinding.FragmentMovieBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var listMovieEntity: List<ListMovieEntity>
    private val movieAdapter = MovieAdapter()
    private var status: Boolean = false

    companion object {
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

            loadData()

            val factory = ViewModelFactory.getInstance(requireContext())
            val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieViewModel.loadPopularMovie().observe(viewLifecycleOwner, { listMovie ->
                listMovieEntity = listMovie
                if (listMovie.isEmpty()) {
                    Log.i(TAG, "onChange: empty")
                    dataEmpty()
                } else {
                    Log.i(TAG, "onChange: not empty")
                    dataLoaded()

                    movieAdapter.setMovies(listMovie)
                    with(fragmentMovieBinding.rvMovie) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieAdapter
                    }
                }
            })

            fragmentMovieBinding.apply {
                topAppBar.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.sort -> {
                            status = !status
                            sortData(status)
                            true
                        }
                        else -> false
                    }
                }
            }
        }
    }

    private fun sortData(state: Boolean) {
        movieAdapter.sortData(state)
        setSortState(state)
    }

    private fun setSortState(state: Boolean) {
        if (state) {
            showSnackBar(getString(R.string.sorted))
            fragmentMovieBinding.topAppBar.menu.findItem(R.id.sort).setIcon(R.drawable.ic_restore)
        } else {
            showSnackBar(getString(R.string.not_sorted))
            fragmentMovieBinding.topAppBar.menu.findItem(R.id.sort)
                .setIcon(R.drawable.ic_sort_by_alpha)
        }
    }

    private fun loadData() {
        fragmentMovieBinding.apply {
            progressBar.visibility = View.VISIBLE
            layoutDataKosong.visibility = View.GONE
            rvMovie.visibility = View.GONE
        }
    }

    private fun dataEmpty() {
        fragmentMovieBinding.apply {
            layoutDataKosong.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            rvMovie.visibility = View.GONE
        }
    }

    private fun dataLoaded() {
        fragmentMovieBinding.apply {
            layoutDataKosong.visibility = View.GONE
            progressBar.visibility = View.GONE
            rvMovie.visibility = View.VISIBLE
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            fragmentMovieBinding.viewParent,
            message, Snackbar.LENGTH_SHORT
        )
            .show()
    }
}