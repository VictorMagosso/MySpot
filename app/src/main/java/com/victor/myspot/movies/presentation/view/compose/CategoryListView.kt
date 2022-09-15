package com.victor.myspot.movies.presentation.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.victor.myspot.R
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

@Composable
fun CategoryListView(categories: List<MoviesPerCategoryModel>) {
    LazyColumn {
        items(categories) { category ->
            Text(category.title)
            Row {
                Image(
                    painter = painterResource(R.drawable.movie_placeholder),
                    contentDescription = "Adicionar filme",
                    modifier = Modifier.fillMaxHeight().width(50.dp),
                    contentScale = ContentScale.Crop
                )
                LazyRow {
                    items(category.movies) { movie ->
                        Image(
                            painter = rememberAsyncImagePainter(movie.imageUrl),
                            contentDescription = movie.title,
                            modifier = Modifier.fillMaxHeight().width(50.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}