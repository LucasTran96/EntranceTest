/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data.api

import com.huytran.entrancetest.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface EntranceTestApi {


  @Headers("Content-Type: application/json")
  @POST("auth/signup")
  suspend fun signUp(@Body user: User) : Response<UserResponse>

  //categories?pageSize=$100&pageNumber=0"
  @Headers("Content-Type: application/json")
  @GET("categories")
  suspend fun getCategories(@Header("Authorization") authorization : String,@Query("pageSize") pageSize: Int, @Query("pageNumber") pageNumber: Int): Response<CategoryResponse>

}