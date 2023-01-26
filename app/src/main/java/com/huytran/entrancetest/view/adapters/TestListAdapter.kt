package com.huytran.entrancetest.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.huytran.entrancetest.data.model.Category
import kotlinx.android.synthetic.main.item_category.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder


class TestListAdapter : ItemBinder<Category, TestListAdapter.CarViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.huytran.entrancetest.R.layout.item_category, parent, false)
        return CarViewHolder(view)
    }

    override fun canBindData(item: Any): Boolean {
        return item is Category
    }

    override fun bindViewHolder(holder: CarViewHolder, item: Category) {
        Log.d("TestListAdapter", item.name)
        holder.tvCarName.text = item.name

    }

    inner class CarViewHolder(itemView: View) : ItemViewHolder<Category>(itemView) {
        var tvCarName: TextView

        init {
            tvCarName = itemView.rootView.txtCategoryName
            Log.d("TestListAdapter", "${itemView.txtCategoryName.text}")
        }
    }
}