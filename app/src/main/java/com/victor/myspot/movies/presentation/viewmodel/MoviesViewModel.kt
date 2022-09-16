package com.victor.myspot.movies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.domain.usecase.GetFavoriteMoviesUseCase
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
            getFavoriteMovieUseCase().collect { movies ->
                viewState.moviesPerCategoryModel.postValue(movies)
                viewState.isLoading.postValue(false)
            }
        }
    }
}
