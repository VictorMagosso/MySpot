package com.victor.myspot.di

import com.victor.myspot.login.domain.usecase.*
import com.victor.myspot.movies.domain.usecase.GetMovie
import com.victor.myspot.movies.domain.usecase.GetMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<RegisterUserUseCase> { RegisterUser(accountRepository = get()) }
    factory<ExecuteLogoutUseCase> { ExecuteLogout(accountRepository = get()) }
    factory<SignInUseCase> { SignIn(accountRepository = get()) }
    factory<GetMovieUseCase> { GetMovie(moviesRepository = get()) }
}