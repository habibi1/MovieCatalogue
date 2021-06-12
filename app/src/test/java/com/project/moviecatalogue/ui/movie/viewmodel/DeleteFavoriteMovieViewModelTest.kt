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
class DeleteFavoriteMovieViewModelTest {

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
    fun deleteFavoriteMovie() {
        doNothing().`when`(catalogRepository).deleteFavoriteMovie(dataDummyMovie)
        viewModel.deleteFavoriteMovie(dataDummyMovie)

        verify(catalogRepository, times(1)).deleteFavoriteMovie(dataDummyMovie)
    }

}