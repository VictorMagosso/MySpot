package com.victor.myspot.movies.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.repository.IMoviesRepository

class DeleteMovie(
    private val moviesRepository: IMoviesRepository,
) : DeleteMovieUseCase {
    override suspend fun invoke(id: String, category: String): Result<Boolean, String> =
        moviesRepository.deleteMovie(id, category)
}

interface DeleteMovieUseCase {
    suspend operator fun invoke(id: String, category: String): Result<Boolean, String>
}