package com.project.moviecatalogue.ui.tvshow.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getData() {
        val tvShowEntities = viewModel.getData()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}