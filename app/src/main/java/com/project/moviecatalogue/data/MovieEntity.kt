package com.project.moviecatalogue.data

class MovieEntity (
    var id: Int,
    var adult: Boolean,
    var genreIds: String,
    var originalLanguage: String,
    var overview: String,
    var releaseDate: String,
    var posterPath: Int,
    var popularity: Double,
    var title: String,
    var voteAverage: Double,
    var voteCount: Int
)