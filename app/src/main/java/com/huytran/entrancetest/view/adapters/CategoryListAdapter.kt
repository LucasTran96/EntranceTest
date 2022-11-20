/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.huytran.entrancetest.R
import com.huytran.entrancetest.data.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryListAdapter(private val category: MutableList<Category>,
                          private var listener: () -> Unit) : RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_category, parent, false)
    return CategoryHolder(view)
  }

  override fun getItemCount(): Int = category.size

  override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
      holder.bind(category[position], position)
  }

  fun setMovies(categoryList: List<Category>) {
    this.category.clear()
    this.category.addAll(categoryList)
    notifyDataSetChanged()
  }


  inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(category: Category, position: Int) = with(view) {
      txtCategoryName.text = category.name
      view.setOnClickListener {
        category.isSelected = !category.isSelected
        notifyItemChanged(position)
        // listener is used to notify the view of a change.
        listener()
        }
      if (category.isSelected){
        cvMovie.setCardBackgroundColor(ContextCompat.getColor(context, R.color.color_selected))
      }else{
        cvMovie.setCardBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
      }

    }
  }
}