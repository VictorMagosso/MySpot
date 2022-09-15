package com.victor.myspot.movies.presentation.viewintent

sealed class MoviesViewIntent {
    data class GetMovieIntent(val movie: String) : MoviesViewIntent()
}
