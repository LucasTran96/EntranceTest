package com.huytran.uthus.data.module

import com.huytran.uthus.viewmodel.ChildFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var ChildFragmentViewModelModule = module{
    viewModel {
        ChildFragmentViewModel(get())
    }
}