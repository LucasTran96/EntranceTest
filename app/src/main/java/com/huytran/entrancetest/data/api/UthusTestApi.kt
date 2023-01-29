/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data.api

import com.huytran.entrancetest.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface UthusTestApi {


  //?page=1&limit=20
  @Headers("Content-Type: application/json")
  @GET("api-testing/sample-data")
  suspend fun getCategories(@Query("page") page: Int, @Query("limit") limit: Int): Response<BeerResponse>

}