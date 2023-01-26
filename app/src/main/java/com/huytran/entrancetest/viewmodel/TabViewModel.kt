package com.huytran.entrancetest.viewmodel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.huytran.entrancetest.view.activities.ChildFragment
import com.huytran.entrancetest.view.adapters.PagerAdapter


class TabViewModel(contract: MainActivityContract) : ViewModel() {
    interface MainActivityContract {
        fun getFragmentManger(): FragmentManager
    }

    var adapter = PagerAdapter(
        contract.getFragmentManger(),
        listOf("Beer", "Favorite"),
        listOf(
            ChildFragment.newInstance("1"),
            ChildFragment.newInstance("2")
        )
    )

    var currentPosition = 0

    var pageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(p0: Int) {
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(p0: Int) {
            currentPosition = p0
        }
    }

}