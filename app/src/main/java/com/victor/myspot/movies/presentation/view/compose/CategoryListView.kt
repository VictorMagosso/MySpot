package com.victor.myspot.movies.presentation.view.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.victor.myspot.R
import com.victor.myspot.movies.data.model.FavoriteMovieModel
import com.victor.myspot.movies.data.model.MoviesPerCategoryModel
import com.victor.myspot.movies.presentation.viewintent.MoviesViewIntent
import com.victor.myspot.movies.presentation.viewmodel.MoviesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryListView(
    categories: List<MoviesPerCategoryModel>,
    navController: NavController,
    viewModel: MoviesViewModel,
) {
    LazyColumn {
        items(categories.sortedBy { it.category }) { category ->
            Text(
                text = category.category,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
            LazyRow(
                modifier = Modifier
                    .height(250.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                val listWithDefaultItem = category.movies.toMutableList()
                listWithDefaultItem.add(0, FavoriteMovieModel("default"))
                items(listWithDefaultItem) { movie ->
                    if (movie.id == "default") {
                        Image(
                            modifier = Modifier.clickable {
                                navController
                                    .navigate(R.id.action_moviesFragment_to_newMovieFragment)
                            },
                            painter = painterResource(id = R.drawable.add_placeholder),
                            contentDescription = "Adicionar filme"
                        )
                    } else {
                        AsyncImage(
                            model = movie.imageUrl,
                            contentDescription = movie.title,
                            modifier = Modifier
                                .padding(start = 7.dp)
                                .width(140.dp)
                                .fillMaxHeight()
                                .combinedClickable(onLongClick = {
                                    viewModel.dispatchViewIntent(
                                        MoviesViewIntent.DeleteMovie(
                                            movie.id, category.category
                                        )
                                    )
                                }, onClick = {}),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}