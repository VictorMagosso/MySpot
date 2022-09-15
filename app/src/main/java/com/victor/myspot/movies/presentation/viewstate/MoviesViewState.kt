package com.victor.myspot.movies.presentation.viewstate

import androidx.lifecycle.MutableLiveData

class MoviesViewState {
    val isLoading = MutableLiveData(false)
    val action = MutableLiveData<Action>()

    sealed class Action {
        data class ErrorGettingMovie(val message: String) : Action()
    }
}
