package com.victor.myspot.movies.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.repository.IMoviesRepository

class GetMovieFromApi(
    private val moviesRepository: IMoviesRepository
) : GetMovieFromApiUseCase {
    override suspend fun invoke(movie: String): Result<MovieModel, MovieError> =
        moviesRepository.getMovie(movie)
}

interface GetMovieFromApiUseCase {
    suspend operator fun invoke(movie: String): Result<MovieModel, MovieError>
}
