package com.victor.myspot.movies.presentation.viewintent

sealed class MoviesViewIntent {
    object GetFavoritesMovies : MoviesViewIntent()
}
