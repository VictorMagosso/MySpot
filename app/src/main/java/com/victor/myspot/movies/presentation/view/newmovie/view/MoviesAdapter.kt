package com.victor.myspot.movies.presentation.view.newmovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.myspot.BR
import com.victor.myspot.databinding.ItemMovieBinding
import com.victor.myspot.movies.presentation.view.newmovie.viewstate.ItemUiModel

class MoviesAdapter(
    val list: List<ItemUiModel>
) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ItemMovieBinding.inflate(
            inflater,
            parent,
            false
        )
        return MyViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = list[position]
        holder.binding.setVariable(BR.uiModel, movie)
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}