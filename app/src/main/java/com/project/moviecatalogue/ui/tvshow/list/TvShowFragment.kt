package com.project.moviecatalogue.ui.tvshow.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.moviecatalogue.databinding.FragmentTvShowBinding
import com.project.moviecatalogue.ui.tvshow.viewmodel.TvShowViewModel

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
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getData()

            if (tvShows.isEmpty()) {
                fragmentTvShowBinding.layoutDataKosong.visibility = View.VISIBLE
                fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
                fragmentTvShowBinding.rvTvShow.visibility = View.INVISIBLE
            } else {
                fragmentTvShowBinding.layoutDataKosong.visibility = View.INVISIBLE
                fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
                fragmentTvShowBinding.rvTvShow.visibility = View.VISIBLE

                val tvShowAdapter = TvShowAdapter()
                tvShowAdapter.setTvShows(tvShows)
                with(fragmentTvShowBinding.rvTvShow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
            }
        }
    }
}