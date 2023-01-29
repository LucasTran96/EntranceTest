/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huytran.entrancetest.data.model.*
import com.huytran.entrancetest.data.api.EntranceTestApi
import kotlinx.coroutines.*

class EntranceTestRepositoryImpl(private val apiService: EntranceTestApi) : EntranceTestRepository {

  /**
   * get List Categories is an asynchronous method when querying the server to get a list of categories.
   */
  override fun getListCategories(token: String): LiveData<List<Data>?> {
    val data = MutableLiveData<List<Data>>()
    val pageSize = 1
    val pageNumber = 20
   // val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = apiService.getCategories(pageSize, pageNumber)
      data.postValue(result.body()?.data)
    }
    return data
  }
}


