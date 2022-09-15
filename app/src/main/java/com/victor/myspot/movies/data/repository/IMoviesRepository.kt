package com.victor.myspot.movies.data.repository

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

interface IMoviesRepository {
    suspend fun getMovie(movie: String): Result<MovieModel, MovieError>
    suspend fun saveFavoriteMovie(movie: ItemUiModel): Result<Boolean, String>
}
