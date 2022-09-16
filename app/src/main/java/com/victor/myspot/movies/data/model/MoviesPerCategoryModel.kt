package com.victor.myspot.movies.data.model

import java.io.Serializable

data class MoviesPerCategoryModel(
    val category: String,
    val movies: List<FavoriteMovieModel> = arrayListOf()
) : Serializable
