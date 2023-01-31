package com.huytran.uthus.view.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.huytran.uthus.BR
import com.huytran.uthus.R
import com.huytran.uthus.databinding.ActivityMainBinding
import com.huytran.uthus.viewmodel.TabViewModel

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
