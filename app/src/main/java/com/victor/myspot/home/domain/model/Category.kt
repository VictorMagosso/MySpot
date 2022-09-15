package com.victor.myspot.home.domain.model

data class Category(
    val title: String,
    val subtitle: String,
    val imageUrl: String?,
    val type: CategoryType,
)

enum class CategoryType {
    MUSIC,
    MOVIE,
}
