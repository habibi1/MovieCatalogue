package com.project.moviecatalogue.ui.movie.viewmodel

import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.MovieEntity
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieEntity: MovieEntity

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
        movieEntity = MovieEntity(
            0,
            "Action",
            "en",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "2021-04-29",
            R.drawable.img_poster_movie_1,
            4547.446,
            "Tom Clancy's Without Remorse",
            7.3,
            829,
            "Remaja",
            "1 Jam 49 Menit"
        )
    }

    @Test
    fun getData() {
        val moviesEntities = viewModel.getData()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }

    @Test
    fun getDetail() {
        val movie = viewModel.getDetail(0)
        assertNotNull(movie)
        assertEquals(movie.name, movieEntity.name)
        assertEquals(movie.genreIds, movieEntity.genreIds)
        assertEquals(movie.originalLanguage, movieEntity.originalLanguage)
        assertEquals(movie.overview, movieEntity.overview)
        assertEquals(movie.firstAirDate, movieEntity.firstAirDate)
        assertEquals(movie.posterPath, movieEntity.posterPath)
        assertEquals(movie.popularity.toString(), movieEntity.popularity.toString())
        assertEquals(movie.voteAverage.toString(), movieEntity.voteAverage.toString())
        assertEquals(movie.voteCount, movieEntity.voteCount)
        assertEquals(movie.usia, movieEntity.usia)
        assertEquals(movie.durasi, movieEntity.durasi)
    }
}