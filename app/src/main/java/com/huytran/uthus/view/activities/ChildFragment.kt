package com.huytran.uthus.view.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.huytran.uthus.BR
import com.huytran.uthus.R
import com.huytran.uthus.data.model.Beer
import com.huytran.uthus.databinding.FragmentChildBinding
import com.huytran.uthus.viewmodel.ChildFragmentViewModel
import com.huytran.uthus.viewmodel.ChildFragmentViewModel.Companion.DELETE
import com.huytran.uthus.viewmodel.ChildFragmentViewModel.Companion.SAVE
import com.huytran.uthus.viewmodel.ChildFragmentViewModel.Companion.UPDATE
import com.huytran.uthus.viewmodel.TabViewModel.Companion.TAB2
import kotlinx.android.synthetic.main.fragment_child.*
import org.koin.android.viewmodel.ext.android.viewModel

class ChildFragment : Fragment() {
    private val viewModel : ChildFragmentViewModel by viewModel()
    private var text = ""
    private val TAG = "ChildFragment"
    private val listItem: MutableList<Beer> = mutableListOf()
    private var isOnCreated = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT) ?: ""
        }
        getCategories()
        configureLiveDataObservers()
        isOnCreated = true
    }

    private fun configureLiveDataObservers() {
        viewModel.getButtonSaveLiveData().observe(this, Observer {
            status -> status?.let {
            when(status){
                SAVE -> Toast.makeText(activity, "Save successful.", Toast.LENGTH_SHORT).show()
                UPDATE -> Toast.makeText(activity, "Update successful.", Toast.LENGTH_SHORT).show()
                DELETE -> Toast.makeText(activity, "Delete successful.", Toast.LENGTH_SHORT).show()
            }
            //Toast.makeText(activity, "${it.name}", Toast.LENGTH_LONG).show()
        }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentChildBinding>(inflater,
                R.layout.fragment_child, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        if (isOnCreated){
            getCategories()
        }
    }

    /**
     * getCategories is a fun handler that listens and updates new data from the View model.
     */
    private fun getCategories() {
        activity?.let {
            if (isOnline(it)){
                showLoading()
                activity?.let {
                    viewModel.getListCategories().observe(it,
                        Observer { categorys ->
                            hideLoading()
                            if (categorys == null) {
                                showMessage()
                            } else {
                                if (text == TAB2){
                                    activity?.let {
                                            activity-> viewModel.getAllBeerFromRoom().observe(activity,
                                        Observer { beers ->
                                            Log.d(TAG, "beers.size=${beers?.size}")
                                            if (beers != null){
                                                listItem.clear()
                                                beers.forEach{ beer ->
                                                    beer.isFavorite = true
                                                }
                                                listItem.addAll(beers)
                                                viewModel.adapter.replaceAll(listItem)
                                            }else{
                                                showMessage()
                                            }
                                        })
                                    }
                                }else{
                                    activity?.let {
                                            activity-> viewModel.getAllBeerFromRoom().observe(activity,
                                        Observer { beers ->
                                            //listItem.clear()
                                            Log.d(TAG, "beers.size=${beers?.size}")
                                            if (beers?.size!! > 0){
                                                listItem.clear()
                                                beers.forEach{ beer->
                                                    categorys.forEach{ category->
                                                        if (category.id == beer.id){
                                                            category.isSave = true
                                                            category.note = beer.note
                                                        }
                                                    }
                                                }
                                                Log.d(TAG, "text == \"1\"")
                                                listItem.addAll(categorys)
                                                viewModel.adapter.replaceAll(listItem)
                                            }
                                            else{
                                                //showMessage()
                                                listItem.clear()
                                                listItem.addAll(categorys)
                                                viewModel.adapter.replaceAll(listItem)
                                            }
                                        })
                                    }
                                }
                            }
                        })
                }
            }else{
                hideLoading()

                if (text == TAB2){
                    Log.d(TAG, "text == \"2\"")

                    activity?.let {
                            activity-> viewModel.getAllBeerFromRoom().observe(activity,
                        Observer { beers ->
                            Log.d(TAG, "beers.size=${beers?.size}")
                            if (beers != null){
                                listItem.clear()
                                beers.forEach{ beer ->
                                    beer.isFavorite = true
                                }
                                listItem.addAll(beers)
                                viewModel.adapter.replaceAll(listItem)
                            }else{
                                showMessage()
                            }

                        })
                    }
                }
                Toast.makeText(activity, "No Internet.", Toast.LENGTH_SHORT).show()
            }
        }


    }


    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //It will check for both wifi and cellular network
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
            return false
        } else {
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    /**
     * showMessage is a fun used to display the error returned when querying the api from the server.
     */
    private fun showMessage() {
    }

    // show loading
    private fun showLoading() {
        prLoadingBeer?.visibility = View.VISIBLE
        rclBeer?.isEnabled = false
    }

    // hide loading
    private fun hideLoading() {
        prLoadingBeer?.visibility = View.GONE
        rclBeer?.isEnabled = true
    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance(text: String) =
            ChildFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, text)
                    }
            }
    }
}

