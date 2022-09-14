package com.victor.myspot.di

import com.victor.myspot.login.presentation.viewmodel.CreateAccountViewModel
import com.victor.myspot.login.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel()
    }
    viewModel {
        CreateAccountViewModel(registerUserUseCase = get())
    }
}