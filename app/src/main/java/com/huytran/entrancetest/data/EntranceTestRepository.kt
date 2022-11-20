/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data

import androidx.lifecycle.LiveData
import com.huytran.entrancetest.data.model.*

interface EntranceTestRepository {

  // Sign up a new user
  fun signUp(user : User): LiveData<UserResponse?>

  // get all list categories
  fun getListCategories(token : String): LiveData<List<Category>?>

}