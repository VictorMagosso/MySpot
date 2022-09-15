package com.victor.myspot.di

import com.victor.myspot.movies.data.datasource.Mapper
import com.victor.myspot.movies.data.datasource.MovieResponseToMovieModelMapper
import com.victor.myspot.movies.data.model.MovieModel
import com.victor.myspot.movies.data.model.MovieResponse
import com.victor.myspot.movies.presentation.mapper.MovieModelToMovieUiModelMapper
import com.victor.myspot.movies.presentation.viewstate.MovieUiModel
import org.koin.dsl.module

val mapperModule = module {
    factory { MovieResponseToMovieModelMapper() }
    factory { MovieModelToMovieUiModelMapper() }
}