package com.huytran.entrancetest.view.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.DateValidatorPointBackward.before
import com.huytran.entrancetest.BR
import com.huytran.entrancetest.data.model.Data
import com.huytran.entrancetest.databinding.ItemCategoryBinding
import org.jetbrains.anko.sdk25.coroutines.onTouch


class BeerAdapter(
    private val layouts: Map<Int, Int>,
    dataList: List<Data>,
    private val listener: Any? = null
): RecyclerView.Adapter<BeerAdapter.HobbiesViewHolder>() {

    private val dataList = ObservableArrayList<Data>().apply {
        addAll(dataList)
    }

    class HobbiesViewHolder(val viewBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(viewBinding.root){

         fun bind(data: Data) {

             data.let {
                 viewBinding.txtBeerPrice.text = it.price

                 //viewBinding.txtCategoryName.text = it.name
                 //Picasso.get().load("https://placekitten.com/200/200").into(viewBinding.imgBeer)

                 viewBinding.txtNotice.addTextChangedListener(object : TextWatcher{
                     override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                         Log.d("ChildFragmentViewModel", "CharSequence=$p0")
                         Log.d("ChildFragmentViewModel", "onTextChanged=${viewBinding.edtNotice.editText?.text.toString()}")

                         it.note = p0.toString()
                     }

                     override fun afterTextChanged(p0: Editable?) {
                         Log.d("ChildFragmentViewModel", "afterTextChanged=$p0")
                     }

                     override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                         Log.d("ChildFragmentViewModel", "beforeTextChanged=$p0")
                     }
                 })
             }
        }
    }

    //  class Holder(val bind: ViewDataBinding) : RecyclerView.ViewHolder(bind.root)
    //
    //    constructor(id: Int, items: List<T>, listener: Any? = null) : this(mapOf(0 to id), items, listener)

    constructor(id: Int, dataList: List<Data>, listener: Any? = null) : this(mapOf(0 to id), dataList, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbiesViewHolder {
//        val view = ItemCategoryBinding.inflate(LayoutInflater.from(context) , parent,false)
//        return HobbiesViewHolder(view)
        Log.d("ChildFragmentViewModel", "onCreateViewHolder")
        return LayoutInflater.from(parent.context).let {
            val id = layouts[viewType]!!
            val bind = DataBindingUtil.inflate<ItemCategoryBinding>(it, id, parent, false)
            return@let HobbiesViewHolder(bind)
        }
    }

    //override fun onBindViewHolder(holder: Holder, position: Int) {
    //        holder.bind.setVariable(BR.model, getItemByPosition(position))
    //        listener?.let { holder.bind.setVariable(BR.listener, it) }
    //        holder.bind.executePendingBindings()
    //    }
    override fun onBindViewHolder(holder: HobbiesViewHolder, position: Int) {
        Log.d("ChildFragmentViewModel", "onBindViewHolder")
        holder.viewBinding.setVariable(BR.model, getItemByPosition(position))
        listener?.let { holder.viewBinding.setVariable(BR.listener, it) }
        holder.viewBinding.executePendingBindings()
        val data = dataList[position]
        holder.bind(data)
    }

    open fun getItemByPosition(position: Int): Data = dataList[position]

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun replaceAll(items: List<Data>) {
        this.dataList.clear()
        this.dataList.addAll(items)
        notifyDataSetChanged()
    }

    fun remove(item: Data) {
        dataList.remove(item)
        notifyDataSetChanged()
    }



    fun refresh() {
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

}