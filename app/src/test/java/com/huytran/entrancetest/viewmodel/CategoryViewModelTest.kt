package com.huytran.entrancetest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.huytran.entrancetest.data.EntranceTestRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest{

  private lateinit var categoryViewModel: CategoryViewModel

  @Mock
  lateinit var repository: EntranceTestRepository

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Before
  fun setup(){
    categoryViewModel = CategoryViewModel(repository)
  }

  @Test
  fun cantGetListCategoriesWithoutToken(){
    val token = ""
    val canSignUp = categoryViewModel.getListCategories(token)
    assertEquals(null, canSignUp)
  }

  @Test
  fun cantGetListCategoriesWithRandomToken(){
    val token = "sdjdjsdhjdhsdshjwewi233223jk3jk32"
    val canSignUp = categoryViewModel.getListCategories(token)
    assertEquals(null, canSignUp)
  }

}