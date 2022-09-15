package com.victor.myspot.core.presentation

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.victor.myspot.R

fun ImageView.renderImage(
    url: String,
) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.movie_placeholder)
        .error(R.drawable.movie_placeholder)
        .into(this)
}