package com.victor.myspot.home.domain.factory

import com.victor.myspot.home.domain.model.Category
import com.victor.myspot.home.domain.model.CategoryType

object CategoriesFactory {
    fun createCategories() = listOf(
        Category("Músicas", "Veja suas músicas disponíveis", "", CategoryType.MUSIC),
        Category("Filmes", "Veja seus filmes disponíveis", "", CategoryType.MOVIE)
    )
}