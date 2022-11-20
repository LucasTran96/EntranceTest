/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huytran.entrancetest.data.model.*
import com.huytran.entrancetest.data.api.EntranceTestApi
import com.huytran.entrancetest.data.api.RetrofitClient
import kotlinx.coroutines.*

class EntranceTestRepositoryImpl(private val apiService: EntranceTestApi) : EntranceTestRepository {

  /**
   * signUp is an asynchronous method when querying the server to register a new user.
   */
  override fun signUp(user: User): LiveData<UserResponse?> {
    val data = MutableLiveData<UserResponse>()
    //val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = apiService.signUp(user)
      data.postValue(result.body())
    }
    return data
  }

  /**
   * get List Categories is an asynchronous method when querying the server to get a list of categories.
   */
  override fun getListCategories(token: String): LiveData<List<Category>?> {
    val data = MutableLiveData<List<Category>>()
    val pageSize = 100
    val pageNumber = 0
   // val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = apiService.getCategories(token, pageSize, pageNumber)
      data.postValue(result.body()?.categories)
    }
    return data
  }
}


