package com.project.moviecatalogue.data.source.local.entity

data class DetailTvShowEntity (
    val id: Int,
    val name: String,
    val status: String,
    val firstAirDate: String,
    val numberOfEpisodes: Int,
    val voteAverage: Double,
    val popularity: Double,
    val voteCount: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String
)