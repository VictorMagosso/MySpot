package com.victor.myspot.movies.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.victor.myspot.core.util.Result
import com.victor.myspot.core.util.encodedString
import com.victor.myspot.movies.data.api.MoviesApi
import com.victor.myspot.movies.data.model.*
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import kotlinx.coroutines.tasks.await

class MoviesDataSource(
    private val moviesApi: MoviesApi,
    private val mapper: MovieResponseToMovieModelMapper,
    private val firebaseAuth: FirebaseAuth,
    private val dbRef: DatabaseReference,
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

    override suspend fun saveFavoriteMovie(movie: ItemUiModel): Result<Boolean, String> {
        return try {
            firebaseAuth.currentUser?.let { loggedUser ->
                getMovieReference(loggedUser)
                    .push()
                    .setValue(mapFrom(movie))
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Result.Success(true)
                        } else {
                            Result.Error("erro")
                        }
                    }.await()
            }
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    private fun getMovieReference(loggedUser: FirebaseUser) = dbRef
        .child(DatabaseColumns.USER)
        .child(loggedUser.uid)
        .child(DatabaseColumns.MOVIES)

    private fun mapFrom(movie: ItemUiModel): FavoriteMovieModel =
        FavoriteMovieModel(
            id = movie.title.encodedString(),
            title = movie.title,
            voteAverage = movie.voteAverage,
            imageUrl = movie.imageUrl
        )
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