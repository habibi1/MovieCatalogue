package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.local.entity.ListTvShowEntity
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private var dataDummyListTvShow = DataDummy.generateDummyListTvShow()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<ListTvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun loadPopularTvShow() {
        val tvShow = MutableLiveData<List<ListTvShowEntity>>()
        tvShow.value = dataDummyListTvShow

        `when`(catalogRepository.getPapularTvShow()).thenReturn(tvShow)

        val dataListTvShow = viewModel.loadPopularTvShow().value

        verify(catalogRepository).getPapularTvShow()
        assertNotNull(dataListTvShow)
        assertEquals(dataDummyListTvShow.size, dataListTvShow?.size)

        viewModel.loadPopularTvShow().observeForever(observer)
        verify(observer).onChanged(dataDummyListTvShow)
    }
}