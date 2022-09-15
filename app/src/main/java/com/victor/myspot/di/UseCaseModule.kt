package com.victor.myspot.di

import com.victor.myspot.login.domain.usecase.*
import com.victor.myspot.movies.domain.usecase.GetMovieFromApi
import com.victor.myspot.movies.domain.usecase.GetMovieFromApiUseCase
import com.victor.myspot.movies.domain.usecase.SaveFavoriteMovie
import com.victor.myspot.movies.domain.usecase.SaveFavoriteMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<RegisterUserUseCase> { RegisterUser(accountRepository = get()) }
    factory<ExecuteLogoutUseCase> { ExecuteLogout(accountRepository = get()) }
    factory<SignInUseCase> { SignIn(accountRepository = get()) }
    factory<GetMovieFromApiUseCase> { GetMovieFromApi(moviesRepository = get()) }
    factory<SaveFavoriteMovieUseCase> { SaveFavoriteMovie(moviesRepository = get()) }
}