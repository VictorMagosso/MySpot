package com.victor.myspot.movies.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.data.repository.IMoviesRepository

class GetFavoriteMovies(private val moviesRepository: IMoviesRepository) :
    GetFavoriteMoviesUseCase {
    override suspend fun invoke(): Result<List<MoviesPerCategoryModel>, String> =
        moviesRepository.getFavoriteMovies()

}

interface GetFavoriteMoviesUseCase {
    suspend operator fun invoke(): Result<List<MoviesPerCategoryModel>, String>
}
