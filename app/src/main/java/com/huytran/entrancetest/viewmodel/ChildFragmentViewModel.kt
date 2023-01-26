package com.huytran.entrancetest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.huytran.entrancetest.R
import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.model.Category
import com.huytran.entrancetest.view.adapters.BindAdapter


class ChildFragmentViewModel(private val repository: EntranceTestRepository): ViewModel()   {


    val adapter = BindAdapter(
        id = R.layout.list_item,
        items = listOf<Category>(),
        listener = this
    )

    fun btnClkEvent(model: Category) {
        Log.d("ChildFragmentViewModel", model.name)
        //contract.showToast(model.btnText)
    }

    /**
     * getListCategories is a fun handler that calls EntranceTestRepositoryImpl to get the Category List for any view that observes it.
     */
    fun getListCategories(token: String): LiveData<List<Category>?> {
        return repository.getListCategories(token)
    }
}