package com.huytran.uthus.view.activities

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
import com.huytran.uthus.data.db.SessionManager
import com.huytran.uthus.data.model.Beer
import com.huytran.uthus.databinding.FragmentChildBinding
import com.huytran.uthus.viewmodel.ChildFragmentViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

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
        //activity?.let { viewModel.onCreate(it) }
        getCategories()
        configureLiveDataObservers()
        isOnCreated = true
    }

    private fun configureLiveDataObservers() {
        viewModel.getButtonSaveLiveData().observe(this, Observer {
            isSave -> isSave?.let {
            Toast.makeText(activity, "${it.name}", Toast.LENGTH_LONG).show()
        }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentChildBinding>(inflater,
                R.layout.fragment_child, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        //val listItem: MutableList<Category> = mutableListOf()
        Log.d("ChildFragmentViewModel", "category size View= ${listItem.size}")
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("ChildFragmentViewModel", "onResume")
        if (isOnCreated){
            getCategories()
        }
    }

    /**
     * getCategories is a fun handler that listens and updates new data from the View model.
     */
    private fun getCategories() {


            showLoading()
            activity?.let {
                viewModel.getListCategories().observe(it,
                    Observer { categorys ->
                        hideLoading()
                        if (categorys == null) {
                            showMessage()
                        } else {
                            if (text == "2"){
                                listItem.clear()
                                Log.d(TAG, "text == \"2\"")

                                activity?.let {
                                    activity-> viewModel.getAllBeerFromRoom().observe(activity,
                                        Observer { beers ->
                                            //listItem.clear()
                                            Log.d("ChildFragmentViewModel", "beers.size=${beers?.size}")
                                        if (beers != null){
                                            listItem.clear()
                                            if (beers.isNotEmpty()){
                                                Log.d("ChildFragmentViewModel", "model.isFavorite = ${beers[0].isFavorite}")
                                            }

                                            beers.forEach{ beer ->
                                                beer.isFavorite = true
                                            }

                                            listItem.addAll(beers)
                                            viewModel.adapter.replaceAll(listItem)
                                            Log.d("ChildFragmentViewModel", "listItem.size=${listItem.size}")
                                            //viewModel.adapter.replaceAll(listItem)
                                        }else{
                                            showMessage()
                                        }

                                    })
                                }
                            }else{
                                listItem.clear()
                                activity?.let {
                                        activity-> viewModel.getAllBeerFromRoom().observe(activity,
                                    Observer { beers ->
                                        //listItem.clear()
                                        Log.d("ChildFragmentViewModel", "beers.size=${beers?.size}")
                                        if (beers != null){
                                            beers.forEach{ beer->
                                                categorys.forEach{ category->
                                                    if (category.id == beer.id){
                                                        category.isSave = true
                                                    }
                                                }
                                            }
                                        }else{
                                            showMessage()
                                        }
                                    })
                                }

                                Log.d(TAG, "text == \"1\"")
                                listItem.addAll(categorys)
                                Log.d("categoryzz", "category size= ${categorys.size}")
                                //adapter.setMovies(category)
                                viewModel.adapter.replaceAll(listItem)

                            }

                        }
                    })
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

