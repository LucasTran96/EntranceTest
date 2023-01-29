package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.data.UthusTestRepository
import com.huytran.entrancetest.data.UthusTestRepositoryImpl
import org.koin.dsl.module

var repositoryModule = module{
    single {
        UthusTestRepositoryImpl(get()) as UthusTestRepository
    }
}