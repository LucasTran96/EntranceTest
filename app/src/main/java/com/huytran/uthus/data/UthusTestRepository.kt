/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.uthus.data

import androidx.lifecycle.LiveData
import com.huytran.uthus.data.model.*

interface UthusTestRepository {


  // get all list categories
  fun getListCategories(): LiveData<List<Beer>?>

  // get all beer list from Room Database
  fun getAllBeerFromRoom(): LiveData<List<Beer>?>

  suspend fun insertBeer(beer: Beer)

  suspend fun updateBeer(beer: Beer)

  suspend fun deleteBeer(beer: Beer)


}