package com.project.moviecatalogue.ui.movie.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getData() {
        val moviesEntities = viewModel.getData()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}