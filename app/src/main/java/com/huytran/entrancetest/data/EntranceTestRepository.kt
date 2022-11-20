/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import com.huytran.entrancetest.data.model.*

interface EntranceTestRepository {

  fun signUp(user : User): LiveData<UserResponse?>

  fun getListCategories(token : String): LiveData<List<Category>?>

}