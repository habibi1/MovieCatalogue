package com.project.moviecatalogue.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteMovieByIdViewModelTest {

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
    fun getFavoriteMovieById() {
        val movie = MutableLiveData<DetailMovieEntity>()
        movie.value = dataDummyMovie

        Mockito.`when`(catalogRepository.getFavoriteMovieById(dataDummyMovie.id)).thenReturn(movie)

        val dataFavorite =
            viewModel.getFavoriteMovieById(dataDummyMovie.id).value as DetailMovieEntity

        Mockito.verify(catalogRepository).getFavoriteMovieById(dataDummyMovie.id)
        Assert.assertNotNull(dataFavorite)
        Assert.assertEquals(dataFavorite, movie.value)
    }

}