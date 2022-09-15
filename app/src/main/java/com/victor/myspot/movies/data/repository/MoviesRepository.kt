package com.victor.myspot.movies.data.repository

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.datasource.IMoviesDataSource
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel

class MoviesRepository(private val moviesDataSource: IMoviesDataSource) : IMoviesRepository {
    override suspend fun getMovie(movie: String): Result<MovieModel, MovieError> =
        moviesDataSource.getMovie(movie)
}