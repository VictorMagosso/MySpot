package com.victor.myspot.home.presentation.viewmodel

import com.victor.myspot.core.presentation.BaseViewModel
import com.victor.myspot.home.presentation.viewintent.HomeViewIntent
import com.victor.myspot.home.presentation.viewstate.HomeViewState
import com.victor.myspot.login.domain.usecase.ExecuteLogoutUseCase

class HomeViewModel(
    private val logoutUseCase: ExecuteLogoutUseCase,
) : BaseViewModel<HomeViewIntent, HomeViewState>() {
    override fun dispatchViewIntent(intent: HomeViewIntent) {
        when(intent) {
            HomeViewIntent.Logout -> logoutUseCase()
        }
    }

    override val viewState = HomeViewState()
}