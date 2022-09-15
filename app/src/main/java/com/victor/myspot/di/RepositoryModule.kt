package com.victor.myspot.di

import com.victor.myspot.core.FirebaseConfig
import com.victor.myspot.login.data.repository.AccountRepository
import com.victor.myspot.login.data.repository.IAccountRepository
import com.victor.myspot.movies.data.datasource.IMoviesDataSource
import com.victor.myspot.movies.data.datasource.MoviesDataSource
import com.victor.myspot.movies.data.repository.IMoviesRepository
import com.victor.myspot.movies.data.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IAccountRepository> { AccountRepository(FirebaseConfig().getFirebaseAuth()) }
    single<IMoviesRepository> { MoviesRepository(get()) }
}

val dataSourceModule = module {
    single<IMoviesDataSource> { MoviesDataSource(get(), get()) }
}