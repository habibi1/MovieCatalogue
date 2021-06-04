package com.project.moviecatalogue.ui.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.project.moviecatalogue.data.source.remote.repository.RemoteDataSource
import com.project.moviecatalogue.ui.utils.LiveDataTestUtil
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

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

}