package com.victor.myspot.movies.presentation.viewintent

sealed class MoviesViewIntent {
    object GetFavoritesMovies : MoviesViewIntent()
    data class DeleteMovie(val id: String, val category: String) : MoviesViewIntent()
}
