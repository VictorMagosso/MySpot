package com.victor.myspot.movies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.domain.usecase.GetMovieUseCase
import com.victor.myspot.movies.presentation.mapper.MovieModelToMovieUiModelMapper
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
import com.victor.myspot.movies.presentation.viewstate.MoviesViewState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val movieModelToMovieUiModelMapper: MovieModelToMovieUiModelMapper,
) : BaseViewModel<MoviesViewIntent, MoviesViewState>() {
    override val viewState = MoviesViewState()

    override fun dispatchViewIntent(intent: MoviesViewIntent) {
        when (intent) {
            is MoviesViewIntent.GetMovieIntent -> getMovie(intent)
        }
    }

    private fun getMovie(intent: MoviesViewIntent.GetMovieIntent) {
        viewModelScope.launch {
            viewState.isLoading.postValue(true)
            getMovieUseCase(intent.movie).handleResult(
                onSuccess = { updateUiModel(it) },
                onError = { viewState.action.postValue(MoviesViewState.Action.ErrorGettingMovie(it.message)) },
                onFinish = { viewState.isLoading.postValue(false) }
            )
        }
    }

    private fun updateUiModel(model: MovieModel) {
        viewState.movieUiModel.postValue(movieModelToMovieUiModelMapper.mapFrom(model))
    }
}
