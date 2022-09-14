package com.victor.myspot.di

import com.victor.myspot.login.domain.usecase.RegisterUser
import com.victor.myspot.login.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<RegisterUserUseCase> { RegisterUser(accountRepository = get()) }
//    factory<GetSongsUseCase> { GetSongs(tripRepository = get()) }
//    factory<RemoveMovieUseCase> { RemoveMovie(tripRepository = get()) }
//    factory<RemoveSongUseCase> { RemoveSong(tripRepository = get()) }
}