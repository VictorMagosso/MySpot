package com.victor.myspot.login.presentation.viewmodel

import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.login.domain.usecase.RegisterUserUseCase
import com.victor.myspot.login.presentation.viewintent.LoginViewIntent
import com.victor.myspot.login.presentation.viewstate.LoginViewState

class LoginViewModel(
    private val registerUser: RegisterUserUseCase
) : BaseViewModel<LoginViewIntent, LoginViewState>() {
    override fun dispatchViewIntent(intent: LoginViewIntent) {
        TODO("Not yet implemented")
    }

    override val viewState: LoginViewState
        get() = TODO("Not yet implemented")
}