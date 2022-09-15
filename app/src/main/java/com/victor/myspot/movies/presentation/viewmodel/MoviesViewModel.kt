package com.victor.myspot.movies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.domain.usecase.GetFavoriteMoviesUseCase
import com.victor.myspot.movies.domain.usecase.SaveFavoriteMovieUseCase
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
import com.victor.myspot.movies.presentation.viewstate.MoviesViewState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getFavoriteMovieUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel<MoviesViewIntent, MoviesViewState>() {
    override val viewState = MoviesViewState()

    override fun dispatchViewIntent(intent: MoviesViewIntent) {
        when (intent) {
            MoviesViewIntent.GetFavoritesMovies -> getMovies()
        }
    }
    private fun getMovies() {
        viewState.isLoading.postValue(true)
        viewModelScope.launch {
            getFavoriteMovieUseCase().handleResult(
                onSuccess = { model -> viewState.moviesPerCategoryModel.postValue(model) },
                onError = { viewState.action.postValue(MoviesViewState.Action.ErrorGettingMovie(it)) },
                onFinish = { viewState.isLoading.postValue(false) }
            )
        }
    }
}
