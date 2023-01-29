package com.huytran.entrancetest.viewmodel

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huytran.entrancetest.R
import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.model.Data
import com.huytran.entrancetest.view.adapters.BeerAdapter
import com.squareup.picasso.Picasso

class ChildFragmentViewModel(private val repository: EntranceTestRepository): ViewModel()   {


    // saveLiveData is MutableLiveData as Boolean type it will notify every time there is a change in value.
    private var saveLiveData = MutableLiveData<Data>()


    // getSaveLiveData is a fun event that fires an event on which view Observer it when the saveLiveData data is changed.
    fun getButtonSaveLiveData(): LiveData<Data> = saveLiveData

    val adapter = BeerAdapter(
    id= R.layout.item_category,
    dataList = listOf<Data>(),
    listener = this
    )

    fun btnSaveOrDeleteEvent(model: Data) {
        Log.d("ChildFragmentViewModel", "model.isFavorite = ${model.isFavorite}")
        //Toast.makeText(, "${model.name}", Toast.LENGTH_LONG).show()
        //Log.d("ChildFragmentViewModel", "note= ${}")
        Log.d("ChildFragmentViewModel", "CharSequence=${model.note}")
        saveLiveData.postValue(model)

//        if (!model.isFavorite){
//            model.isFavorite = true
//            adapter.remove(model)
//        }else{
//            model.isFavorite = false
//        }
    }


    fun btnUpdateEvent(model: Data) {
        Log.d("ChildFragmentViewModel", model.name)
        //Toast.makeText(, "${model.name}", Toast.LENGTH_LONG).show()
        saveLiveData.postValue(model)
    }

    companion object{

        @BindingAdapter("urlImage")
        @JvmStatic
        fun setImageBeer(imageView : AppCompatImageView, url:String){
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.img_category_person)
                .error(R.drawable.img_category_person)
                .fit()
                .into(imageView)
        }

        @BindingAdapter("buttonVisibility")
        @JvmStatic
        fun bindVisibility(appCompatButton: AppCompatButton, visible: Boolean) {
            appCompatButton.visibility =
                if (visible){
                    View.VISIBLE
                }else{
                    View.GONE
                }
        }
    }

    /**
     * getListCategories is a fun handler that calls EntranceTestRepositoryImpl to get the Category List for any view that observes it.
     */
    fun getListCategories(token: String): LiveData<List<Data>?> {
        return repository.getListCategories(token)
    }
}