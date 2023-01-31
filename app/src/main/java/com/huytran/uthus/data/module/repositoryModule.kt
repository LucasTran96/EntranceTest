package com.huytran.uthus.data.module

import com.huytran.uthus.data.UthusTestRepository
import com.huytran.uthus.data.UthusTestRepositoryImpl
import org.koin.dsl.module

var repositoryModule = module{
    single {
        UthusTestRepositoryImpl(get(), get()) as UthusTestRepository
    }
}