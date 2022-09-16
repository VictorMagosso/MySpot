package com.victor.myspot.movies.data.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.victor.myspot.core.util.Result
import com.victor.myspot.core.util.encodedString
import com.victor.myspot.movies.data.api.MoviesApi
import com.victor.myspot.movies.data.model.*
import com.victor.myspot.movies.presentation.factory.CategoriesFactory.makeCategories
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    override suspend fun saveFavoriteMovie(
        movie: ItemUiModel,
        category: String
    ): Result<Boolean, String> {
        return try {
            firebaseAuth.currentUser?.let { loggedUser ->
                getMovieReference(loggedUser, category)
                    .child(movie.title.encodedString())
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

    override suspend fun getFavoriteMovies(): Flow<List<MoviesPerCategoryModel>> = callbackFlow {
        val movies: MutableList<MoviesPerCategoryModel> = arrayListOf()
        val itemMovies = arrayListOf<FavoriteMovieModel>()
        val valueListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(movies.toList())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("cancelado", "cancelou antes")
                close()
            }

        }
        try {
            firebaseAuth.currentUser?.let { safeUser ->
                makeCategories().forEach { category ->
                    getCategoriesReference(safeUser, category)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (ds: DataSnapshot in snapshot.children) {
                                    ds.getValue(FavoriteMovieModel::class.java)
                                        ?.let { favoriteMovies ->
                                            itemMovies.add(favoriteMovies)
                                        }
                                }
                                movies.add(
                                    MoviesPerCategoryModel(category, itemMovies.toList())
                                )
                                trySend(movies.toList())
                                itemMovies.clear()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.d("cancelado", "cancelou")
                                close()
                            }
                        })
                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }

        awaitClose {
            makeCategories().forEach { category ->
                firebaseAuth.currentUser?.let { safeUser ->
                    getCategoriesReference(safeUser, category).removeEventListener(valueListener)
                }
            }
        }
    }

    override suspend fun deleteMovie(id: String, category: String): Result<Boolean, String> = try {
        firebaseAuth.currentUser?.let { safeUser ->
            getMovieReference(safeUser, category)
                .child(id)
                .removeValue()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Result.Success(true)
                    } else {
                        Log.d("error", "erro ao deletar")
                        Result.Error("Erro ao deletar")
                    }
                }.await()
        }
        Result.Success(true)
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }


    private fun getMovieReference(loggedUser: FirebaseUser, category: String) = dbRef
        .child(DatabaseColumns.USER)
        .child(loggedUser.uid)
        .child(category)
        .child(DatabaseColumns.MOVIES)

    private fun getCategoriesReference(loggedUser: FirebaseUser, category: String) = dbRef
        .child(DatabaseColumns.USER)
        .child(loggedUser.uid)
        .child(category)
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