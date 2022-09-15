package com.victor.myspot.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.victor.myspot.R
import com.victor.myspot.home.domain.factory.CategoriesFactory
import com.victor.myspot.home.domain.model.Category
import com.victor.myspot.home.domain.model.CategoryType

class CategoriesAdapter(
    private val list: List<Category> = CategoriesFactory.createCategories()
) : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_category, parent, false
            )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = list[position]
        holder.let { v ->
            v.title?.text = category.title
            v.subtitle?.text = category.subtitle

            when(category.type) {
                CategoryType.MUSIC -> v.image?.setImageResource(R.drawable.ic_music)
                CategoryType.MOVIE -> v.image?.setImageResource(R.drawable.ic_video)
            }
        }
    }

    override fun getItemCount() = list.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var subtitle: TextView? = null
        var image: ImageView? = null

        init {
            title = itemView.findViewById(R.id.text_title)
            subtitle = itemView.findViewById(R.id.text_subtitle)
            image = itemView.findViewById(R.id.image_category)
        }
    }
}