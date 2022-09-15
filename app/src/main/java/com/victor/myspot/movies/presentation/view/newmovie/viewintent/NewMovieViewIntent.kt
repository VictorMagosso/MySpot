package com.victor.myspot.movies.presentation.view.newmovie.viewintent

sealed class NewMovieViewIntent {
    data class GetMovieIntent(val movie: String) : NewMovieViewIntent()
}
