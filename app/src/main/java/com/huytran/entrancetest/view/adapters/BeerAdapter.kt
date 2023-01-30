package com.huytran.entrancetest.view.adapters

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.huytran.entrancetest.BR
import com.huytran.entrancetest.data.model.Beer
import com.huytran.entrancetest.databinding.ItemCategoryBinding


class BeerAdapter(
    private val layouts: Map<Int, Int>,
    dataList: List<Beer>,
    private val listener: Any? = null
): RecyclerView.Adapter<BeerAdapter.HobbiesViewHolder>() {

    private val dataList = ObservableArrayList<Beer>().apply {
        addAll(dataList)
    }

    class HobbiesViewHolder(val viewBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(viewBinding.root){

         fun bind(data: Beer) {

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

    constructor(id: Int, dataList: List<Beer>, listener: Any? = null) : this(mapOf(0 to id), dataList, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbiesViewHolder {
        Log.d("ChildFragmentViewModel", "onCreateViewHolder")
        return LayoutInflater.from(parent.context).let {
            val id = layouts[viewType]!!
            val bind = DataBindingUtil.inflate<ItemCategoryBinding>(it, id, parent, false)
            return@let HobbiesViewHolder(bind)
        }
    }

    override fun onBindViewHolder(holder: HobbiesViewHolder, position: Int) {
        Log.d("ChildFragmentViewModel", "onBindViewHolder")
        holder.viewBinding.setVariable(BR.model, getItemByPosition(position))
        listener?.let { holder.viewBinding.setVariable(BR.listener, it)}
        holder.viewBinding.listener?.isButtonSave?.set(holder.viewBinding.btnSave.text.toString())
        holder.viewBinding.executePendingBindings()
        val data = dataList[position]
        holder.bind(data)
    }

    open fun getItemByPosition(position: Int): Beer = dataList[position]

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun replaceAll(items: List<Beer>) {
        this.dataList.clear()
        this.dataList.addAll(items)
        notifyDataSetChanged()
    }

    fun remove(item: Beer) {
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