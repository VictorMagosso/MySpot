package com.victor.myspot.core.data.api

import com.victor.myspot.BuildConfig

private const val spotifyKey = BuildConfig.SPOTIFY_KEY
private const val moviesKey = BuildConfig.MOVIES_KEY

object UrlFactory {
    const val SPOTIFY_BASE_URL = "https://api.spotify.com/v1/"
    const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/"

    const val MOVIE_BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
}