package com.victor.myspot.login.presentation.viewstate

import androidx.lifecycle.MutableLiveData

class LoginViewState {
    val isLoading = MutableLiveData(false)
    val viewAction = MutableLiveData<Action>()

    sealed class Action {
        object NavigateToHomeFragment : Action()
        data class ShowErrorToast(val message: String): Action()
    }
}