package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.viewmodel.ChildFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var ChildFragmentViewModelModule = module{
    viewModel {
        ChildFragmentViewModel(get())
    }
}