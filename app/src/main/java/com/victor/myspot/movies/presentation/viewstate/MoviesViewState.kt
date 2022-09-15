package com.victor.myspot.movies.presentation.viewstate

import androidx.lifecycle.MutableLiveData

class MoviesViewState {
    val isLoading = MutableLiveData(false)
    val action = MutableLiveData<Action>()

    val movieUiModel = MutableLiveData<MovieUiModel>()

    sealed class Action {
        data class ErrorGettingMovie(val message: String) : Action()
    }
}

data class MovieUiModel(
    val title: String,
    val image: String,
    val voteAverage: Double,
)
