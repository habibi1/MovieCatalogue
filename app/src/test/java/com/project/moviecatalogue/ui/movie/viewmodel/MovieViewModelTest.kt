package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.ListMovieEntity
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private var dataDummyListMovie = DataDummy.generateDummyListMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<ListMovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun loadPopularMovie() {
        val movie = MutableLiveData<List<ListMovieEntity>>()
        movie.value = dataDummyListMovie

        `when`(catalogRepository.getPapularMovies()).thenReturn(movie)

        val dataListMovie = viewModel.loadPopularMovie().value

        Mockito.verify(catalogRepository).getPapularMovies()
        assertNotNull(dataListMovie)
        assertEquals(dataDummyListMovie.size, dataListMovie?.size)

        viewModel.loadPopularMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummyListMovie)
    }
}