package com.huytran.entrancetest.view.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.huytran.entrancetest.BR
import com.huytran.entrancetest.R
import com.huytran.entrancetest.action
import com.huytran.entrancetest.data.db.SessionManager
import com.huytran.entrancetest.data.model.Category
import com.huytran.entrancetest.databinding.FragmentChildBinding
import com.huytran.entrancetest.snack
import com.huytran.entrancetest.viewmodel.ChildFragmentViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChildFragment : Fragment() {
    private val viewModel : ChildFragmentViewModel by viewModel()
    private var text = ""
    private lateinit var sessionManager: SessionManager
    private val listItem: MutableList<Category> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT) ?: ""
        }
        sessionManager = activity?.let { SessionManager(it) }!!
        getCategories()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentChildBinding>(inflater,
                R.layout.fragment_child, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        //val listItem: MutableList<Category> = mutableListOf()
        Log.d("categoryzz", "category size View= ${listItem.size}")
        if (text == "2"){

            //listItem.add(Category("5","6 Fragment", false))

//            listItem.add(Category("7","6 Fragment", false))
//            listItem.add(Category("8","6 Fragment", false))
//            listItem.add(Category("9","6 Fragment", false))
//            listItem.add(ModelItem("4 Fragment", "4 Fragment\nBtnClick"))
//            listItem.add(ModelItem("5 Fragment", "5 Fragments\nBtnClick"))
        }else{
            //getCategories()
            listItem.clear()
            listItem.add(Category("5","6 Fragment", false))
            listItem.add(Category("7","6 Fragment", false))
            listItem.add(Category("8","6 Fragment", false))
            listItem.add(Category("9","6 Fragment", false))
//            listItem.add(ModelItem("9 Fragment", "9 Fragment\nBtnClick"))
//            listItem.add(ModelItem("10 Fragment", "10 Fragments\nBtnClick"))
        }


        //viewModel.adapter.replaceAll(listItem)

        return binding.root
    }

    /**
     * getCategories is a fun handler that listens and updates new data from the View model.
     */
    private fun getCategories() {

        sessionManager.fetchAuthToken()?.let {
            showLoading()
            val bearer = "Bearer"
            activity?.let {
                viewModel.getListCategories(token = "$bearer ${sessionManager.fetchAuthToken()}").observe(
                    it, Observer { category ->
                        //        activity?.let {
                        //            viewModel.getListCategories(token = "$bearer ${sessionManager.fetchAuthToken()}").observe(

                        hideLoading()
                        if (category == null) {
                            showMessage()
                        } else {


                            if (text == "2"){

                                listItem.clear()
                                listItem.add(Category("5","6 Fragment", false))
                                listItem.add(Category("7","7 Fragment", false))
                                listItem.add(Category("8","8 Fragment", false))
                                listItem.add(Category("9","9 Fragment", false))
                            }else{
                                listItem.clear()
                                listItem.addAll(category)
                                Log.d("categoryzz", "category size= ${category.size}")
                                //adapter.setMovies(category)

                            }
                            viewModel.adapter.replaceAll(listItem)
                        }
                    })
            }


        }
    }

    /**
     * showMessage is a fun used to display the error returned when querying the api from the server.
     */
    private fun showMessage() {
//        categoryLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
//            action(getString(R.string.ok)) {
//                getCategories()
//            }
//        }
    }

    // show loading
    private fun showLoading() {
//        categoryProgressBar.visibility = View.VISIBLE
//        categoryRecyclerView.isEnabled = false
    }

    // hide loading
    private fun hideLoading() {
//        categoryProgressBar.visibility = View.GONE
//        categoryRecyclerView.isEnabled = true
    }


    //private fun createVm() = ChildFragmentViewModel()

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

