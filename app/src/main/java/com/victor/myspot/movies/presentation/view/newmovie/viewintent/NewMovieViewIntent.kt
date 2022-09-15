package com.victor.myspot.movies.presentation.view.newmovie.viewintent

import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

sealed class NewMovieViewIntent {
    data class GetMovieIntent(val movie: String) : NewMovieViewIntent()
    data class SaveMovie(val favoriteMovie: ItemUiModel) : NewMovieViewIntent()
}
