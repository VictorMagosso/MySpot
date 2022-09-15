package com.victor.myspot.movies.presentation.viewmodel

import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
import com.victor.myspot.movies.presentation.viewstate.MoviesViewState

class MoviesViewModel() : BaseViewModel<MoviesViewIntent, MoviesViewState>() {
    override val viewState = MoviesViewState()

    override fun dispatchViewIntent(intent: MoviesViewIntent) {
        when (intent) {

        }
    }
}
