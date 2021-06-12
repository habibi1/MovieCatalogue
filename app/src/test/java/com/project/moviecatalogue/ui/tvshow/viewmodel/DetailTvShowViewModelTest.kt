package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private val dummyMovie = DataDummy.generateDummyDetailTvShow()
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<DetailTvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun loadDetailTvShow() {

        val tvShowDummy = MutableLiveData<DetailTvShowEntity>()
        tvShowDummy.value = dummyMovie

        `when`(catalogRepository.getDetailTvShow(movieId)).thenReturn(tvShowDummy)

        val movieData = viewModel.loadDetailTvShow(movieId).value as DetailTvShowEntity

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.name, movieData.name)
        assertEquals(dummyMovie.numberOfEpisodes, movieData.numberOfEpisodes)
        assertEquals(dummyMovie.firstAirDate, movieData.firstAirDate)
        assertEquals(dummyMovie.status, movieData.status)
        assertEquals(dummyMovie.voteAverage, movieData.voteAverage)
        assertEquals(dummyMovie.popularity, movieData.popularity)
        assertEquals(dummyMovie.voteCount, movieData.voteCount)
        assertEquals(dummyMovie.originalLanguage, movieData.originalLanguage)
        assertEquals(dummyMovie.overview, movieData.overview)
        assertEquals(dummyMovie.posterPath, movieData.posterPath)
        assertEquals(dummyMovie.backdropPath, movieData.backdropPath)

        viewModel.loadDetailTvShow(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}