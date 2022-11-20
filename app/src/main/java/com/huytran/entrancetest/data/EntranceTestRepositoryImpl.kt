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

class EntranceTestRepositoryImpl : EntranceTestRepository {

  override fun signUp(user: User): LiveData<UserResponse?> {
    val data = MutableLiveData<UserResponse>()

    val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = entranceTestApi.signUp(user)
      data.postValue(result.body())
      //Log.d("UserTesst", "Response SignUp: ${result.body()}")
    }
    return data
  }

  override fun getListCategories(token: String): LiveData<List<Category>?> {
    val data = MutableLiveData<List<Category>>()
    val pageSize = 100
    val pageNumber = 0
    val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = entranceTestApi.getCategories(token, pageSize, pageNumber)
      data.postValue(result.body()?.categories)

      //Log.d("UserTesst", "Response searchMovies: ${result.body()?.categories}")
    }
    return data
  }
}


