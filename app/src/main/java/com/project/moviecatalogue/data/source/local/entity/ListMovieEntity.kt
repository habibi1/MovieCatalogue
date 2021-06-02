package com.project.moviecatalogue.data.source.local.entity

data class ListMovieEntity (
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posterPath: String
)