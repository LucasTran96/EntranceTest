/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.model.Category


class CategoryViewModel(private val repository: EntranceTestRepository): ViewModel()  {

  /**
   * getListCategories is a fun handler that calls EntranceTestRepositoryImpl to get the Category List for any view that observes it.
   */
  fun getListCategories(token: String): LiveData<List<Category>?> {
    return repository.getListCategories(token)
  }
}