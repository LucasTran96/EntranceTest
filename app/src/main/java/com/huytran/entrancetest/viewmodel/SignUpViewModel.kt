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


class SignUpViewModel(private val repository: EntranceTestRepository = EntranceTestRepositoryImpl()): ViewModel() {

  var email = ObservableField<String>("")
  var password = ObservableField<String>("")
  var isChecked = ObservableField<Boolean>(false)


  private val saveLiveData = MutableLiveData<Boolean>()

  fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

  fun signUp(user: User): LiveData<UserResponse?> {
    return repository.signUp(user)
  }

  fun saveMovie() {
    if (isChecked.get() == true) {
      val user = User(email.get().toString(), "Huy", "Quoc Tran", password.get().toString())
      repository.signUp(user)
      saveLiveData.postValue(true)
    } else {
      saveLiveData.postValue(false)
    }
  }

}