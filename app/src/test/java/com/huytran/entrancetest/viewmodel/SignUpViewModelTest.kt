package com.huytran.entrancetest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.model.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignUpViewModelTest{

  private lateinit var addViewModel: SignUpViewModel

  @Mock
  lateinit var repository: EntranceTestRepository

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Before
  fun setup(){
    addViewModel = SignUpViewModel(repository)
  }

  @Test
  fun cantSignUpWithoutEmail(){

    val user = User("","Huy","Quoc Tran","1234567")
    val canSignUp = addViewModel.signUp(user)
    assertEquals(null, canSignUp)
  }

  @Test
  fun cantSignUpWithoutPassword(){
    val user = User("tranquochuytest111@gmail.con","Huy","Quoc Tran","")
    val canSignUp = addViewModel.signUp(user)
    assertEquals(null, canSignUp)
  }

  @Test
  fun cantSignUpWithEmailAndPassword(){
    val user = User("tranquochuytestwed3@gmail.con","Huy","Quoc Tran","132dd3434")
    val canSignUp = addViewModel.signUp(user)
    assertEquals(null, canSignUp)
  }

}