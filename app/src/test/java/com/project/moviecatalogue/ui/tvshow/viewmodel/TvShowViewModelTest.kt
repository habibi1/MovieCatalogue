package com.project.moviecatalogue.ui.tvshow.viewmodel

import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.TvShowEntity
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowEntity: TvShowEntity

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
        tvShowEntity = TvShowEntity(
            0,
            "Action",
            "en",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "2021-03-19",
            R.drawable.img_poster_tv_show_1,
            1306.109,
            "The Falcon and the Winter Soldier",
            7.9,
            5484,
            "50 Menit",
            "6"
        )
    }

    @Test
    fun getData() {
        val tvShowEntities = viewModel.getData()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }

    @Test
    fun getDetail() {
        val tvShow = viewModel.getDetail(0)
        assertNotNull(tvShow)
        assertEquals(tvShow.name, tvShowEntity.name)
        assertEquals(tvShow.genreIds, tvShowEntity.genreIds)
        assertEquals(tvShow.originalLanguage, tvShowEntity.originalLanguage)
        assertEquals(tvShow.overview, tvShowEntity.overview)
        assertEquals(tvShow.firstAirDate, tvShowEntity.firstAirDate)
        assertEquals(tvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(tvShow.popularity.toString(), tvShowEntity.popularity.toString())
        assertEquals(tvShow.voteAverage.toString(), tvShowEntity.voteAverage.toString())
        assertEquals(tvShow.voteCount, tvShowEntity.voteCount)
        assertEquals(tvShow.jumlahEpisode, tvShowEntity.jumlahEpisode)
        assertEquals(tvShow.durasi, tvShowEntity.durasi)
    }
}