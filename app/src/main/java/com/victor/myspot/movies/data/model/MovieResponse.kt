package com.victor.myspot.movies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<ItemResponse>,
    @field:Json(name = "total_pages") val total_pages: Int,
    @field:Json(name = "total_results") val total_results: Int,
)

@JsonClass(generateAdapter = true)
data class ItemResponse(
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "genre_ids") val genreIds: List<Int>,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "video") val video: Boolean,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int,
)
