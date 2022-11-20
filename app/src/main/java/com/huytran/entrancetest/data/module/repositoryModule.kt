package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.data.EntranceTestRepository
import com.huytran.entrancetest.data.EntranceTestRepositoryImpl
import org.koin.dsl.module

var repositoryModule = module{
    single {
        EntranceTestRepositoryImpl(get()) as EntranceTestRepository
    }
}