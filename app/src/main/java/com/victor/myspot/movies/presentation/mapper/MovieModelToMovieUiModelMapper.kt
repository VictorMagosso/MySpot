package com.victor.myspot.movies.presentation.mapper

import com.victor.myspot.movies.data.datasource.Mapper
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.presentation.viewstate.MovieUiModel

class MovieModelToMovieUiModelMapper : Mapper<MovieModel, MovieUiModel> {
    override fun mapFrom(from: MovieModel): MovieUiModel {
        return MovieUiModel(
            title = from.title,
            image = from.backdropPath,
            voteAverage = from.voteAverage
        )
    }
}