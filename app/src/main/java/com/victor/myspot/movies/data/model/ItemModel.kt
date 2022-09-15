package com.victor.myspot.movies.data.model

data class ItemModel(
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Double,
)
