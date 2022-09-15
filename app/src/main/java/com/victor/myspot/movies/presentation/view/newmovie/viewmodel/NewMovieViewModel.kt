package com.victor.myspot.movies.presentation.view.newmovie.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.domain.usecase.GetMovieFromApiUseCase
import com.victor.myspot.movies.presentation.mapper.MovieModelToMovieUiModelMapper
import com.victor.myspot.movies.presentation.view.newmovie.viewintent.NewMovieViewIntent
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.NewMovieViewState
import kotlinx.coroutines.launch

class NewMovieViewModel(
    private val getMovieUseCase: GetMovieFromApiUseCase,
    private val movieModelToMovieUiModelMapper: MovieModelToMovieUiModelMapper,
) : BaseViewModel<NewMovieViewIntent, NewMovieViewState>() {
    override val viewState = NewMovieViewState()

    override fun dispatchViewIntent(intent: NewMovieViewIntent) {
        when (intent) {
            is NewMovieViewIntent.GetMovieIntent -> getMovie(intent)
        }
    }

    private fun getMovie(intent: NewMovieViewIntent.GetMovieIntent) {
        viewModelScope.launch {
            viewState.isLoading.postValue(true)
            getMovieUseCase(intent.movie).handleResult(
                onSuccess = { updateUiModel(it) },
                onError = { viewState.action.postValue(NewMovieViewState.Action.ErrorGettingMovie(it.message)) },
                onFinish = { viewState.isLoading.postValue(false) }
            )
        }
    }

    private fun updateUiModel(model: MovieModel) {
        viewState.movieUiModel.postValue(movieModelToMovieUiModelMapper.mapFrom(model))
    }
}