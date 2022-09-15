package com.victor.myspot.di

import com.victor.myspot.movies.data.datasource.MovieResponseToMovieModelMapper
import com.victor.myspot.movies.presentation.mapper.MovieModelToMovieUiModelMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MovieResponseToMovieModelMapper() }
    factory { MovieModelToMovieUiModelMapper() }
}