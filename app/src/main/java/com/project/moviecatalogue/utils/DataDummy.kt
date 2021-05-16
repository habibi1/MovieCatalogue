package com.project.moviecatalogue.utils

import com.project.moviecatalogue.data.MovieEntity

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                1,
                false,
                "en",
                "en",
                "Tes",
                "dsds",
                "tanggal",
                "poster",
                2.34,
                "Test",
                true,
                2.34,
                23
            )
        )

        return movie
    }

}