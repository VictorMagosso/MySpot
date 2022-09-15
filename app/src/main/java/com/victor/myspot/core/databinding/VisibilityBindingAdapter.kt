package com.victor.myspot.core.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["visible"])
fun visible(view: View, shouldDisplay: Boolean) {
    view.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
}