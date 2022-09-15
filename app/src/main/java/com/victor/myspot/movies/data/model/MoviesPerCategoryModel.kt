package com.victor.myspot.movies.data.model

data class MoviesPerCategoryModel(
    val title: String,
    val movies: List<Movies>
)

data class Movies(
    val imageUrl: String,
    val title: String,
    val voteAverage: Double
)
