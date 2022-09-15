package com.victor.myspot.movies.presentation.mapper

import com.victor.myspot.movies.data.datasource.Mapper
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.MovieUiModel

class MovieModelToMovieUiModelMapper : Mapper<MovieModel, MovieUiModel> {
    override fun mapFrom(from: MovieModel): MovieUiModel {
        val items = mutableListOf<ItemUiModel>()

        from.items.forEach { item ->
            items.add(
                ItemUiModel(
                    title = item.originalTitle,
                    image = item.backdropPath,
                    voteAverage = item.voteAverage
                )
            )
        }
        return MovieUiModel(items = items)
    }
}