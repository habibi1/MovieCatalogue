package com.project.moviecatalogue.data.source.local.entity

data class ListTvShowEntity (
    val id: Int,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posterPath: String
)