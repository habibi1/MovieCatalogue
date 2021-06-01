package com.project.moviecatalogue.ui.tvshow.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentTvShowBinding
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel
import com.project.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

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

            fragmentTvShowBinding.layoutDataKosong.visibility = View.GONE
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            fragmentTvShowBinding.rvTvShow.visibility = View.GONE

            val factory = ViewModelFactory.getInstance()
            val tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowViewModel.loadPopularTvShow().observe(viewLifecycleOwner, { listTvShow ->
                if (listTvShow.isEmpty()) {
                    fragmentTvShowBinding.layoutDataKosong.visibility = View.VISIBLE
                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                    fragmentTvShowBinding.rvTvShow.visibility = View.GONE
                } else {
                    fragmentTvShowBinding.layoutDataKosong.visibility = View.GONE
                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                    fragmentTvShowBinding.rvTvShow.visibility = View.VISIBLE

                    val tvShowAdapter = TvShowAdapter()
                    tvShowAdapter.setTvShows(listTvShow)
                    with(fragmentTvShowBinding.rvTvShow) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvShowAdapter
                    }
                }
            })
        }
    }
}