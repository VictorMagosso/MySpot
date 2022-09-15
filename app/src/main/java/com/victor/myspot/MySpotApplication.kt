package com.victor.myspot

import android.app.Application
import com.victor.myspot.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MySpotApplication  : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MySpotApplication)

            modules(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                networkModule,
                dataSourceModule,
                mapperModule,
            )
        }
    }
}