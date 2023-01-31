package com.huytran.uthus.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.huytran.uthus.view.activities.ChildFragment
import com.huytran.uthus.view.adapters.PagerAdapter


class TabViewModel(contract: MainActivityContract) : ViewModel() {
    interface MainActivityContract {
        fun getFragmentManger(): FragmentManager
    }

    var adapter = PagerAdapter(
        contract.getFragmentManger(),
        listOf("Beer", "Favorite"),
        listOf(
            ChildFragment.newInstance(TAB1),
            ChildFragment.newInstance(TAB2)
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
            adapter.getItem(p0).onResume()
            adapter.notifyDataSetChanged()
            Log.d("ChildFragmentViewModel", "onPageSelected currentPosition = $p0")


        }
    }

    companion object{
        const val TAB1 = "1"
        const val TAB2 = "2"
    }

}