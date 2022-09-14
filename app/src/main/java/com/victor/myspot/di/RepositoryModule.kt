package com.victor.myspot.di

import com.victor.myspot.core.FirebaseConfig
import com.victor.myspot.login.data.repository.AccountRepository
import com.victor.myspot.login.data.repository.IAccountRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IAccountRepository> { AccountRepository(FirebaseConfig().getFirebaseAuth()) }
}