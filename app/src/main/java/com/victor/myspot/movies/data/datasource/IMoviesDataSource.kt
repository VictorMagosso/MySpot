package com.victor.myspot.movies.data.datasource

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel

interface IMoviesDataSource {
    suspend fun getMovie(movie: String) : Result<MovieModel, MovieError>
}
