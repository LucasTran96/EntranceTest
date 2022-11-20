/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

  companion object {
    private const val BASE_URL = "http://streaming.nexlesoft.com:4000/api/"
      private val builder = OkHttpClient.Builder()
      private val okHttpClient: OkHttpClient = builder.build()
      fun getInstance(): Retrofit {
          return Retrofit.Builder().baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(okHttpClient)
              .build()
      }
  }
}