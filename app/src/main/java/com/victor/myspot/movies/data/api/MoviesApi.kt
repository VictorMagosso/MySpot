package com.victor.myspot.movies.data.api

import com.victor.myspot.BuildConfig
import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val KEY = BuildConfig.MOVIES_KEY

interface MoviesApi {
    @GET("search/movie?api_key=$KEY")
    suspend fun getMovie(@Query("query", encoded = true) movie: String): MovieResponse
}