package com.victor.myspot.movies.presentation.viewstate

import androidx.lifecycle.MutableLiveData
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel

class MoviesViewState {
    val isLoading = MutableLiveData(false)
    val action = MutableLiveData<Action>()
    val moviesPerCategoryModel = MutableLiveData<List<MoviesPerCategoryModel>>()

    sealed class Action {
        data class ErrorDeletingMovie(val message: String) : Action()
    }
}
