package com.victor.myspot.movies.data.repository

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.datasource.IMoviesDataSource
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val moviesDataSource: IMoviesDataSource) : IMoviesRepository {
    override suspend fun getMovie(movie: String): Result<MovieModel, MovieError> =
        moviesDataSource.getMovie(movie)

    override suspend fun saveFavoriteMovie(
        movie: ItemUiModel,
        category: String
    ): Result<Boolean, String> =
        moviesDataSource.saveFavoriteMovie(movie, category)

    override suspend fun getFavoriteMovies(): Flow<List<MoviesPerCategoryModel>> =
        moviesDataSource.getFavoriteMovies()
}