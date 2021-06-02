package com.project.moviecatalogue.data.source.local.entity

data class DetailMovieEntity (
    val id: Int,
    val title: String,
    val runtime: Int,
    val releaseDate: String,
    val status: String,
    val voteAverage: Double,
    val popularity: Double,
    val voteCount: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String
)