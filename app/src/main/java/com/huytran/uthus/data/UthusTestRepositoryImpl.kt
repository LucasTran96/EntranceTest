/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.uthus.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huytran.uthus.data.model.*
import com.huytran.uthus.data.api.UthusTestApi
import com.huytran.uthus.data.db.BeerDatabase
import kotlinx.coroutines.*

class UthusTestRepositoryImpl(private val apiService: UthusTestApi, private val db: BeerDatabase) : UthusTestRepository {

  /**
   * get List Categories is an asynchronous method when querying the server to get a list of categories.
   */
  override fun getListCategories(): LiveData<List<Beer>?> {
    val data = MutableLiveData<List<Beer>>()
    val pageSize = 1
    val pageNumber = 20
   // val entranceTestApi =RetrofitClient.getInstance().create(EntranceTestApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
      val result = apiService.getCategories(pageSize, pageNumber)
      data.postValue(result.body()?.data)
    }
    return data
  }

  override fun getAllBeerFromRoom(): LiveData<List<Beer>?> {
    return  db.beerDao().getAllBeer()
  }

  override suspend fun insertBeer(beer: Beer) {
    return db.beerDao().insertBeer(beer)
  }

  override suspend fun updateBeer(beer: Beer) {
    return db.beerDao().updateBeer(beer)
  }

  override suspend fun deleteBeer(beer: Beer) {
    return db.beerDao().beerDelete(beer)
  }
}


