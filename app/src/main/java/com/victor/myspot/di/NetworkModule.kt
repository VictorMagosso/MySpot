package com.victor.myspot.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.victor.myspot.core.data.api.UrlFactory
import com.victor.myspot.movies.data.api.MoviesApi
import com.victor.myspot.songs.data.api.SpotifyApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val networkModule = module {
    factory { provideMoviesService(get()) }
    factory { provideSpotifyService(get()) }

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

}

fun provideMoviesService(
    factory: Moshi
): MoviesApi {
    return Retrofit.Builder()
        .baseUrl(UrlFactory.MOVIES_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create()
}

fun provideSpotifyService(
    factory: Moshi
): SpotifyApi {
    return Retrofit.Builder()
        .baseUrl(UrlFactory.SPOTIFY_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create()
}

