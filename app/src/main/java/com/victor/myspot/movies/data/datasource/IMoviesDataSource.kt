package com.victor.myspot.movies.data.datasource

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

interface IMoviesDataSource {
    suspend fun getMovie(movie: String) : Result<MovieModel, MovieError>
    suspend fun saveFavoriteMovie(movie: ItemUiModel) : Result<Boolean, String>
}
