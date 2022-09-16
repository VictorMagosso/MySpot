package com.victor.myspot.movies.data.repository

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getMovie(movie: String): Result<MovieModel, MovieError>
    suspend fun saveFavoriteMovie(movie: ItemUiModel, category: String): Result<Boolean, String>
    suspend fun getFavoriteMovies(): Flow<List<MoviesPerCategoryModel>>
}
