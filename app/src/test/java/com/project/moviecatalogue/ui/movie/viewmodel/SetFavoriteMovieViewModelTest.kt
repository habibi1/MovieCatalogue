package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SetFavoriteMovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private var dataDummyMovie = DataDummy.generateDummyDetailMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun setFavoriteMovie() {
        doNothing().`when`(catalogRepository).setFavoriteMovie(dataDummyMovie)
        viewModel.setFavoriteMovie(dataDummyMovie)

        verify(catalogRepository, times(1)).setFavoriteMovie(dataDummyMovie)
    }
}