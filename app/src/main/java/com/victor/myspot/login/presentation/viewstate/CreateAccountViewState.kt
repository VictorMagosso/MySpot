package com.victor.myspot.login.presentation.viewstate

import androidx.lifecycle.MutableLiveData

class CreateAccountViewState {
    val isButtonLoading = MutableLiveData(false)
    val viewAction = MutableLiveData<Action>()

    sealed class Action {
        object NavigateToHome : Action()
        data class ShowErrorMessage(val message: String) : Action()
    }
}