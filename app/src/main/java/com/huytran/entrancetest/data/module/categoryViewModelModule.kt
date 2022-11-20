package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.viewmodel.CategoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var categoryViewModelModule = module{
    viewModel {
        CategoryViewModel(get())
    }
}