package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.ui.movie.viewmodel.MovieViewModel
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

    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun loadDetailMovie() {

        val movieDummy = MutableLiveData<DetailMovieEntity>()
        movieDummy.value = dummyMovie

        `when`(catalogRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.loadDetailMovie(movieId).value as DetailMovieEntity

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.title, movieData.title)
        assertEquals(dummyMovie.runtime, movieData.runtime)
        assertEquals(dummyMovie.releaseDate, movieData.releaseDate)
        assertEquals(dummyMovie.status, movieData.status)
        assertEquals(dummyMovie.voteAverage, movieData.voteAverage)
        assertEquals(dummyMovie.popularity, movieData.popularity)
        assertEquals(dummyMovie.voteCount, movieData.voteCount)
        assertEquals(dummyMovie.originalLanguage, movieData.originalLanguage)
        assertEquals(dummyMovie.overview, movieData.overview)
        assertEquals(dummyMovie.posterPath, movieData.posterPath)
        assertEquals(dummyMovie.backdropPath, movieData.backdropPath)

        viewModel.loadDetailMovie(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}