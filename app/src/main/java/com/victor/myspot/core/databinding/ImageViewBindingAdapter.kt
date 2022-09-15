package com.victor.myspot.core.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.victor.myspot.core.presentation.renderImage

@BindingAdapter(value = ["loadImage"])
fun loadImage(imageView: ImageView, url: String?) {
    imageView.renderImage(url.orEmpty())
}
