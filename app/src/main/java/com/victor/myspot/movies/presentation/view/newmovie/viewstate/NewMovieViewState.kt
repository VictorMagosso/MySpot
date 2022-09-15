package com.victor.myspot.movies.presentation.view.newmovie.viewstate

import androidx.lifecycle.MutableLiveData
import com.victor.myspot.core.data.api.UrlFactory.MOVIE_BASE_URL_IMAGE

class NewMovieViewState {
    val isLoading = MutableLiveData(false)
    val action = MutableLiveData<Action>()

    val movieUiModel = MutableLiveData<MovieUiModel>()

    sealed class Action {
        data class ErrorGettingMovie(val message: String) : Action()
    }
}

data class MovieUiModel(
    val items: List<ItemUiModel>,
)

data class ItemUiModel(
    val title: String,
    val image: String,
    val voteAverage: Double,
) {
    val imageUrl: String
        get() = MOVIE_BASE_URL_IMAGE + image

    val voteAverageToString: String
        get() = "Avaliação: ${voteAverage.toString().replace(".", ",")}"
}
