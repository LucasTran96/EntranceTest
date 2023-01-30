/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import com.huytran.entrancetest.data.model.*

interface UthusTestRepository {


  // get all list categories
  fun getListCategories(token : String): LiveData<List<Beer>?>

  // get all beer list from Room Database
  fun getAllBeerFromRoom(): LiveData<List<Beer>?>

  suspend fun insertBeer(beer: Beer)

  suspend fun updateBeer(beer: Beer)

  suspend fun deleteBeer(beer: Beer)


}