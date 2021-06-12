package com.project.moviecatalogue.ui.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.project.moviecatalogue.data.source.local.LocalDataSource
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity
import com.project.moviecatalogue.data.source.remote.RemoteDataSource
import com.project.moviecatalogue.ui.utils.LiveDataTestUtil
import com.project.moviecatalogue.ui.utils.PagedListUtil
import com.project.moviecatalogue.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovieResponse = DataDummy.generateDummyListMovie()
    private val movieId = listMovieResponse[1].id
    private val listTvShowResponse = DataDummy.generateDummyListTvShow()
    private val tvShowId = listTvShowResponse[0].id
    private val detailMovieResponse = DataDummy.generateDummyDetailMovie()
    private val detailTvShowResponse = DataDummy.generateDummyDetailTvShow()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getPopularMovies() {

        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback).onAllMoviesReceived(listMovieResponse)
                null
            }.`when`(remote).getPopularMovies(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getPapularMovies())

        runBlocking {
            verify(remote).getPopularMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieReceived(detailMovieResponse)
                null
            }.`when`(remote).getDetailMovie(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailMovie(movieId))

        runBlocking {
            verify(remote).getDetailMovie(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(detailMovieResponse.id, data.id)
    }

    @Test
    fun getPapularTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShowCallback).onAllTvShowReceived(listTvShowResponse)
                null
            }.`when`(remote).getPopularTvSHow(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getPapularTvShow())

        runBlocking {
            verify(remote).getPopularTvSHow(any())
        }

        assertNotNull(data)
        assertEquals(listTvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowReceived(detailTvShowResponse)
                null
            }.`when`(remote).getDetailTvShow(eq(tvShowId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailTvShow(tvShowId))

        runBlocking {
            verify(remote).getDetailTvShow(eq(tvShowId), any())
        }

        assertNotNull(data)
        assertEquals(detailTvShowResponse.id, data.id)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailMovieEntity>
        `when`(local.getListFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteMovies()

        val movieEntity = PagedListUtil.mockPagedList(DataDummy.generateDummyListMovie())
        verify(local).getListFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(listMovieResponse.size, movieEntity.size)
    }

    @Test
    fun getListFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailTvShowEntity>
        `when`(local.getListFavoriteTvShow()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteTvShow()

        val tvShowEntity = PagedListUtil.mockPagedList(DataDummy.generateDummyListTvShow())
        verify(local).getListFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(listTvShowResponse.size, tvShowEntity.size)
    }

}