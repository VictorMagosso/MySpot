package com.victor.myspot.di

import com.victor.myspot.home.presentation.viewmodel.HomeViewModel
import com.victor.myspot.login.presentation.viewmodel.CreateAccountViewModel
import com.victor.myspot.login.presentation.viewmodel.LoginViewModel
import com.victor.myspot.movies.presentation.view.newmovie.viewmodel.NewMovieViewModel
import com.victor.myspot.movies.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(signInUseCase = get())
    }
    viewModel {
        CreateAccountViewModel(registerUserUseCase = get())
    }
    viewModel {
        HomeViewModel(logoutUseCase = get())
    }
    viewModel {
        MoviesViewModel(getFavoriteMovieUseCase = get())
    }
    viewModel {
        NewMovieViewModel(get(), get(), get())
    }
}