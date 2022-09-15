package com.victor.myspot.login.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.login.domain.usecase.SignInUseCase
import com.victor.myspot.login.presentation.viewintent.LoginViewIntent
import com.victor.myspot.login.presentation.viewstate.LoginViewState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel<LoginViewIntent, LoginViewState>() {
    override val viewState = LoginViewState()

    override fun dispatchViewIntent(intent: LoginViewIntent) {
        when (intent) {
            is LoginViewIntent.SignInIntent -> signIn(intent)
        }
    }

    private fun signIn(intent: LoginViewIntent.SignInIntent) {
        viewModelScope.launch {
            viewState.isLoading.postValue(false)
            signInUseCase(intent.email, intent.password).handleResult(
                onSuccess = {
                    viewState.viewAction.postValue(LoginViewState.Action.NavigateToHomeFragment)
                },
                onError = { errorMessage ->
                    viewState.viewAction.postValue(LoginViewState.Action.ShowErrorToast(errorMessage))
                },
                onFinish = { viewState.isLoading.postValue(false) }
            )
        }
    }
}