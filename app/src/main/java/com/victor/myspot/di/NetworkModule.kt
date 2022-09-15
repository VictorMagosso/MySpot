package com.victor.myspot.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.victor.myspot.core.data.api.UrlFactory
import com.victor.myspot.movies.data.api.MoviesApi
import com.victor.myspot.songs.data.api.SpotifyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val OK_HTTP = "OK http"
val networkModule = module {
    factory<MoviesApi> { provideApi(get(), get(), UrlFactory.MOVIES_BASE_URL) }
    factory<SpotifyApi> { provideApi(get(), get(), UrlFactory.SPOTIFY_BASE_URL) }

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

    single {
        val interceptor = HttpLoggingInterceptor {
            Log.d(OK_HTTP, it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}

inline fun <reified T> provideApi(
    factory: Moshi,
    client: OkHttpClient,
    baseUrl: String
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .client(client)
        .build()
        .create(T::class.java)
}

