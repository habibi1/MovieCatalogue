package com.project.moviecatalogue.ui.tvshow.viewmodel

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
class SetFavoriteTvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private var dataDummyTvShow = DataDummy.generateDummyDetailTvShow()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun setFavoriteTvShow() {
        doNothing().`when`(catalogRepository).setFavoriteTvShow(dataDummyTvShow)
        viewModel.setFavoriteTvShow(dataDummyTvShow)

        verify(catalogRepository, times(1)).setFavoriteTvShow(dataDummyTvShow)
    }

}