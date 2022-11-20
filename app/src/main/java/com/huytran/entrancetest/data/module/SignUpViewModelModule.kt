package com.huytran.entrancetest.data.module

import com.huytran.entrancetest.viewmodel.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var SignUpViewModelModule = module{

    viewModel {
        SignUpViewModel(get())
    }
}