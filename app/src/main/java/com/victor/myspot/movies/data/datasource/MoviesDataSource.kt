package com.victor.myspot.movies.data.datasource

import com.victor.myspot.core.util.Result
import com.victor.myspot.movies.data.api.MoviesApi
import com.victor.myspot.movies.data.model.ItemModel
import com.victor.myspot.movies.data.model.MovieError
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.model.MovieResponse

class MoviesDataSource(
    private val moviesApi: MoviesApi,
    private val mapper: MovieResponseToMovieModelMapper
) : IMoviesDataSource {
    override suspend fun getMovie(movie: String): Result<MovieModel, MovieError> {
        return try {
            val response = moviesApi.getMovie(movie)
            Result.Success(mapper.mapFrom(response))
        } catch (e: Exception) {
            e.message
            Result.Error(MovieError())
        }
    }
}

class MovieResponseToMovieModelMapper : ResponseToModelMapper<MovieResponse, MovieModel>() {
    override fun mapFrom(from: MovieResponse): MovieModel {
        val items = mutableListOf<ItemModel>()
        from.results.forEach { itemResponse ->
            items.add(
                ItemModel(
                    posterPath = itemResponse.posterPath.orEmpty(),
                    overview = itemResponse.overview,
                    releaseDate = itemResponse.releaseDate,
                    originalTitle = itemResponse.originalTitle,
                    originalLanguage = itemResponse.originalLanguage,
                    title = itemResponse.title,
                    backdropPath = itemResponse.backdropPath.orEmpty(),
                    voteCount = itemResponse.voteCount,
                    voteAverage = itemResponse.voteAverage,
                    video = itemResponse.video,
                )
            )
        }
        return MovieModel(items = items)
    }
}

abstract class ResponseToModelMapper<I, O> : Mapper<I, O>

interface Mapper<I, O> {
    fun mapFrom(from: I): O
}