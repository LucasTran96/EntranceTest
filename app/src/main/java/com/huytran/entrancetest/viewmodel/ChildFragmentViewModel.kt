package com.huytran.entrancetest.viewmodel

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huytran.entrancetest.R
import com.huytran.entrancetest.data.UthusTestRepository
import com.huytran.entrancetest.data.model.Beer
import com.huytran.entrancetest.view.adapters.BeerAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildFragmentViewModel(private val repository: UthusTestRepository): ViewModel()   {


    // saveLiveData is MutableLiveData as Boolean type it will notify every time there is a change in value.
    private var saveLiveData = MutableLiveData<Beer>()
//    lateinit var database: BeerDatabase
//    private lateinit var beerDao: BeerDao

     var isButtonSave = ObservableField<String>("")

    // getSaveLiveData is a fun event that fires an event on which view Observer it when the saveLiveData data is changed.
    fun getButtonSaveLiveData(): LiveData<Beer> = saveLiveData

    val adapter = BeerAdapter(
    id= R.layout.item_category,
    dataList = listOf<Beer>(),
    listener = this
    )

//    fun onCreate(context: Context) {
//        database = Room.databaseBuilder(context, BeerDatabase::class.java, "beer-db").build()
//        beerDao = database.beerDao()
//    }

    fun btnSaveOrDeleteEvent(model: Beer) {

       // model.isFavorite = !model.isFavorite

        Log.d("ChildFragmentViewModel", "isButtonSave=${isButtonSave.get()}")
        if (isButtonSave.get() == "Save")
        {
            model.isFavorite = true
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertBeer(beer = model)
                adapter.remove(model) // xu ly them
                Log.d("ChildFragmentViewModel", "model.isFavorite = ${model.isFavorite}")
                //Toast.makeText(, "${model.name}", Toast.LENGTH_LONG).show()
                //Log.d("ChildFragmentViewModel", "note= ${}")
                Log.d("ChildFragmentViewModel", "CharSequence=${model.note}")
                saveLiveData.postValue(model)
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteBeer(beer = model)
                adapter.remove(model) // xu ly them
                Log.d("ChildFragmentViewModel", "model.isFavorite = ${model.isFavorite}")
                //Toast.makeText(, "${model.name}", Toast.LENGTH_LONG).show()
                //Log.d("ChildFragmentViewModel", "note= ${}")
                Log.d("ChildFragmentViewModel", "CharSequence=${model.note}")
                saveLiveData.postValue(model)
            }
        }






//        if (!model.isFavorite){
//            model.isFavorite = true
//            adapter.remove(model)
//        }else{
//            model.isFavorite = false
//        }
    }

    fun getAllBeerFromRoom() = repository.getAllBeerFromRoom()

    fun btnUpdateEvent(model: Beer) {
        Log.d("ChildFragmentViewModel", model.name)
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateBeer(beer = model)
        }
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
    fun getListCategories(token: String): LiveData<List<Beer>?> {
        return repository.getListCategories(token)
    }
}