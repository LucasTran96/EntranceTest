/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import com.huytran.entrancetest.data.model.*

interface EntranceTestRepository {


  // get all list categories
  fun getListCategories(token : String): LiveData<List<Data>?>

}