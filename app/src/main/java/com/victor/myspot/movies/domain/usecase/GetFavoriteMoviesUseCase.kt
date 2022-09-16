package com.victor.myspot.movies.domain.usecase

import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMovies(private val moviesRepository: IMoviesRepository) :
    GetFavoriteMoviesUseCase {
    override suspend fun invoke(): Flow<List<MoviesPerCategoryModel>> =
        moviesRepository.getFavoriteMovies()

}

interface GetFavoriteMoviesUseCase {
    suspend operator fun invoke(): Flow<List<MoviesPerCategoryModel>>
}
