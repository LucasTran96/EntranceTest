package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.data.api.UthusTestApi
import org.koin.dsl.module
import retrofit2.Retrofit

var apiModule = module {
    fun provideApi(retrofit: Retrofit): UthusTestApi {
        return retrofit.create(UthusTestApi::class.java)
    }

    single { provideApi(get()) }
}