package com.victor.myspot.core.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<I, S> : ViewModel(), PresentationViewModel<I, S>

interface PresentationViewModel<I, S> {
    fun dispatchViewIntent(intent: I)
    val viewState: S
}
