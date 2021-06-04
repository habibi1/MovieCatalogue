package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
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

        Mockito.`when`(catalogRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.loadDetailMovie(movieId).value as DetailMovieEntity

        TestCase.assertNotNull(movieData)
        TestCase.assertEquals(dummyMovie.id, movieData.id)
        TestCase.assertEquals(dummyMovie.title, movieData.title)
        TestCase.assertEquals(dummyMovie.runtime, movieData.runtime)
        TestCase.assertEquals(dummyMovie.releaseDate, movieData.releaseDate)
        TestCase.assertEquals(dummyMovie.status, movieData.status)
        TestCase.assertEquals(dummyMovie.voteAverage, movieData.voteAverage)
        TestCase.assertEquals(dummyMovie.popularity, movieData.popularity)
        TestCase.assertEquals(dummyMovie.voteCount, movieData.voteCount)
        TestCase.assertEquals(dummyMovie.originalLanguage, movieData.originalLanguage)
        TestCase.assertEquals(dummyMovie.overview, movieData.overview)
        TestCase.assertEquals(dummyMovie.posterPath, movieData.posterPath)
        TestCase.assertEquals(dummyMovie.backdropPath, movieData.backdropPath)

        viewModel.loadDetailMovie(movieId).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}