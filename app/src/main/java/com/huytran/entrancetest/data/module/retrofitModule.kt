package com.huytran.entrancetest.data.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    /*
    private const val BASE_URL = "http://streaming.nexlesoft.com:4000/api/"
      private val builder = OkHttpClient.Builder()
      private val okHttpClient: OkHttpClient = builder.build()
      fun getInstance(): Retrofit {
          return Retrofit.Builder().baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(okHttpClient)
              .build()
      }
     */

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        //https://apps.uthus.vn/api/api-testing/sample-data?page=1&limit=20
        return Retrofit.Builder()
            .baseUrl("https://apps.uthus.vn/api/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}