package com.victor.myspot.login.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.login.data.repository.ErrorCreatingAccount
import com.victor.myspot.login.domain.usecase.IsUserLoggedInUseCase
import com.victor.myspot.login.domain.usecase.RegisterUserUseCase
import com.victor.myspot.login.presentation.viewintent.CreateAccountViewIntent
import com.victor.myspot.login.presentation.viewstate.CreateAccountViewState
import kotlinx.coroutines.launch

class CreateAccountViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
) : BaseViewModel<CreateAccountViewIntent, CreateAccountViewState>() {

    override val viewState = CreateAccountViewState()

    override fun dispatchViewIntent(intent: CreateAccountViewIntent) {
        when (intent) {
            is CreateAccountViewIntent.RegisterUserIntent -> registerUser(intent)
            CreateAccountViewIntent.CheckUserAuth -> checkIfUserIsValid()
        }
    }

    private fun checkIfUserIsValid() {
        if (isUserLoggedInUseCase()) {
            viewState.viewAction.postValue(CreateAccountViewState.Action.NavigateToHome)
        }
    }

    private fun registerUser(intent: CreateAccountViewIntent.RegisterUserIntent) {
        viewState.isButtonLoading.postValue(true)
        viewModelScope.launch {
            registerUserUseCase(intent.email, intent.password).handleResult(
                onSuccess = { success ->
                    if (success) {
                        viewState.viewAction.postValue(CreateAccountViewState.Action.NavigateToHome)
                    } else {
                        viewState.viewAction.postValue(
                            CreateAccountViewState.Action.ShowErrorMessage(
                                ErrorCreatingAccount.GENERIC_ERROR.message
                            )
                        )
                    }
                },
                onError = { errorMessage ->
                    viewState.viewAction.postValue(
                        CreateAccountViewState.Action.ShowErrorMessage(
                            errorMessage
                        )
                    )
                },
                onFinish = {
                    viewState.isButtonLoading.postValue(false)
                }
            )
        }
    }
}

