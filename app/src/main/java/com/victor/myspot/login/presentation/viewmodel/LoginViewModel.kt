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
        viewState.isLoading.postValue(true)
        viewModelScope.launch {
            signInUseCase(intent.email, intent.password).getResult(
                onSuccess = {
                    viewState.viewAction.postValue(LoginViewState.Action.NavigateToHomeFragment)
                },
                onFailure = { errorMessage ->
                    viewState.viewAction.postValue(LoginViewState.Action.ShowErrorToast(errorMessage))
                },
                onFinish = { viewState.isLoading.postValue(false) }
            )
        }
    }
}