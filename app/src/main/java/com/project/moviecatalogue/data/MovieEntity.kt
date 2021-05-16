package com.project.moviecatalogue.data

class MovieEntity (
    var id: Int,
    var adult: Boolean,
    var genreIds: String,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var releaseDate: String,
    var posterPath: String,
    var popularity: Double,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
)