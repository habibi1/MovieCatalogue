package com.project.moviecatalogue.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
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
class GetFavoriteTvShowByIdViewModelTest {

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
    fun getFavoriteTvShowById() {
        val tvShow = MutableLiveData<DetailTvShowEntity>()
        tvShow.value = dataDummyTvShow

        Mockito.`when`(catalogRepository.getFavoriteTvShowById(dataDummyTvShow.id))
            .thenReturn(tvShow)

        val dataFavorite =
            viewModel.getFavoriteTvShowById(dataDummyTvShow.id).value as DetailTvShowEntity

        Mockito.verify(catalogRepository).getFavoriteTvShowById(dataDummyTvShow.id)
        Assert.assertNotNull(dataFavorite)
        Assert.assertEquals(dataFavorite, tvShow.value)
    }

}