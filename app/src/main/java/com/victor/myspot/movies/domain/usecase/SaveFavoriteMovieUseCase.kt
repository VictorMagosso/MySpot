package com.victor.myspot.movies.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.repository.IMoviesRepository
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

class SaveFavoriteMovie(
    private val moviesRepository: IMoviesRepository,
) : SaveFavoriteMovieUseCase {
    override suspend fun invoke(
        favoriteMovie: ItemUiModel,
        category: String
    ): Result<Boolean, String> =
        moviesRepository.saveFavoriteMovie(favoriteMovie, category)
}

interface SaveFavoriteMovieUseCase {
    suspend operator fun invoke(
        favoriteMovie: ItemUiModel,
        category: String
    ): Result<Boolean, String>
}
