/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.EntranceTestRepositoryImpl
import com.huytran.entrancetest.data.model.User
import com.huytran.entrancetest.data.model.UserResponse


class SignUpViewModel(private val repository: EntranceTestRepository): ViewModel() {

  var email = ObservableField<String>("")
  var password = ObservableField<String>("")
  var isChecked = ObservableField<Boolean>(false)

  // saveLiveData is MutableLiveData as Boolean type it will notify every time there is a change in value.
  private val saveLiveData = MutableLiveData<Boolean>()

  // getSaveLiveData is a fun event that fires an event on which view Observer it when the saveLiveData data is changed.
  fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

  fun signUp(user: User): LiveData<UserResponse?> {
    return repository.signUp(user)
  }

  /**
   * createNewUser is method bound directly from xml file.
   * It supports handling new user registration.
   */
  fun createNewUser() {
    if (isChecked.get() == true) {
      saveLiveData.postValue(true)
    } else {
      saveLiveData.postValue(false)
    }
  }

}