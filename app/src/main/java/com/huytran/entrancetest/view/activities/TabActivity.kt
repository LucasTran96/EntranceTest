package com.huytran.entrancetest.view.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.huytran.entrancetest.BR
import com.huytran.entrancetest.R
import com.huytran.entrancetest.databinding.ActivityMainBinding
import com.huytran.entrancetest.viewmodel.TabViewModel

class TabActivity : AppCompatActivity() {

    private val viewModel = createVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).setVariable(BR.viewModel,viewModel)
    }

    private fun createVm() = TabViewModel(object : TabViewModel.MainActivityContract {
        override fun getFragmentManger(): FragmentManager = supportFragmentManager
    })
}
