package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.data.api.EntranceTestApi
import org.koin.dsl.module
import retrofit2.Retrofit

var apiModule = module {
    fun provideApi(retrofit: Retrofit): EntranceTestApi {
        return retrofit.create(EntranceTestApi::class.java)
    }

    single { provideApi(get()) }
}