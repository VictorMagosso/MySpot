package com.victor.myspot.movies.presentation.view.newmovie.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.domain.usecase.GetMovieFromApiUseCase
import com.victor.myspot.movies.domain.usecase.SaveFavoriteMovieUseCase
import com.victor.myspot.movies.presentation.mapper.MovieModelToMovieUiModelMapper
import com.victor.myspot.movies.presentation.view.newmovie.viewintent.NewMovieViewIntent
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.NewMovieViewState
import kotlinx.coroutines.launch

class NewMovieViewModel(
    private val getMovieUseCase: GetMovieFromApiUseCase,
    private val movieModelToMovieUiModelMapper: MovieModelToMovieUiModelMapper,
    private val saveFavoriteMovie: SaveFavoriteMovieUseCase,
) : BaseViewModel<NewMovieViewIntent, NewMovieViewState>() {
    override val viewState = NewMovieViewState()

    override fun dispatchViewIntent(intent: NewMovieViewIntent) {
        when (intent) {
            is NewMovieViewIntent.GetMovieIntent -> getMovie(intent)
            is NewMovieViewIntent.SaveMovie -> saveMovie(intent)
        }
    }

    private fun saveMovie(intent: NewMovieViewIntent.SaveMovie) {
        viewModelScope.launch {
            saveFavoriteMovie(intent.favoriteMovie, intent.category).handleResult(
                onSuccess = { showSuccessToast() },
                onError = { viewState.action.postValue(NewMovieViewState.Action.ErrorSavingMovie) },
                onFinish = { viewState.isLoading.postValue(false) }
            )
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

    private fun showSuccessToast() {
        viewState.action.postValue(NewMovieViewState.Action.ShowSuccessToast)
    }

    private fun updateUiModel(model: MovieModel) {
        viewState.movieUiModel.postValue(movieModelToMovieUiModelMapper.mapFrom(model))
    }
}