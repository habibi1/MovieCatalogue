package com.project.moviecatalogue.ui.tvshow.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.moviecatalogue.R
import com.project.moviecatalogue.databinding.FragmentTvShowBinding
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter = TvShowAdapter()
    private var status: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            loadData()

            val factory = ViewModelFactory.getInstance(requireContext())
            val tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowViewModel.loadPopularTvShow().observe(viewLifecycleOwner, { listTvShow ->
                if (listTvShow.isEmpty()) {
                    dataEmpty()
                } else {
                    dataLoaded()

                    tvShowAdapter.setTvShows(listTvShow)
                    with(fragmentTvShowBinding.rvTvShow) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvShowAdapter
                    }
                }
            })

            fragmentTvShowBinding.apply {
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
        tvShowAdapter.sortData(state)
        setSortState(state)
    }

    private fun setSortState(state: Boolean) {
        if (state) {
            showSnackBar(getString(R.string.sorted))
            fragmentTvShowBinding.topAppBar.menu.findItem(R.id.sort).setIcon(R.drawable.ic_restore)
        } else {
            showSnackBar(getString(R.string.not_sorted))
            fragmentTvShowBinding.topAppBar.menu.findItem(R.id.sort)
                .setIcon(R.drawable.ic_sort_by_alpha)
        }
    }

    private fun loadData() {
        fragmentTvShowBinding.apply {
            progressBar.visibility = View.VISIBLE
            layoutDataKosong.visibility = View.GONE
            rvTvShow.visibility = View.GONE
        }
    }

    private fun dataEmpty() {
        fragmentTvShowBinding.apply {
            layoutDataKosong.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            rvTvShow.visibility = View.GONE
        }
    }

    private fun dataLoaded() {
        fragmentTvShowBinding.apply {
            layoutDataKosong.visibility = View.GONE
            progressBar.visibility = View.GONE
            rvTvShow.visibility = View.VISIBLE
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            fragmentTvShowBinding.viewParent,
            message, Snackbar.LENGTH_SHORT
        )
            .show()
    }
}