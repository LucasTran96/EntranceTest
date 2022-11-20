/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.huytran.entrancetest.R
import com.huytran.entrancetest.action
import com.huytran.entrancetest.data.db.SessionManager
import com.huytran.entrancetest.data.model.Category
import com.huytran.entrancetest.snack
import com.huytran.entrancetest.view.adapters.CategoryListAdapter
import com.huytran.entrancetest.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

  private var adapter = CategoryListAdapter(mutableListOf()){ showOrHideButtonDone() }

  //private lateinit var viewModel: CategoryViewModel
  private val viewModel: CategoryViewModel by viewModel()
  private lateinit var sessionManager: SessionManager
  private var categoryList = mutableListOf<Category>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_category)
    //viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    val layoutManager = GridLayoutManager(this, 3)
    // Optionally customize the position you want to default scroll to
    layoutManager.scrollToPosition(0);
    // Attach layout manager to the RecyclerView
    categoryRecyclerView.layoutManager = layoutManager;
    categoryRecyclerView.adapter = adapter
    sessionManager = SessionManager(applicationContext)
    getCategories()
    registerOnBack()
  }

  // show loading
  private fun showLoading() {
    categoryProgressBar.visibility = View.VISIBLE
    categoryRecyclerView.isEnabled = false
  }

  // hide loading
  private fun hideLoading() {
    categoryProgressBar.visibility = View.GONE
    categoryRecyclerView.isEnabled = true
  }

  /**
   * showMessage is a fun used to display the error returned when querying the api from the server.
   */
  private fun showMessage() {
    categoryLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
      action(getString(R.string.ok)) {
        getCategories()
      }
    }
  }

  /**
   * getCategories is a fun handler that listens and updates new data from the View model.
   */
  private fun getCategories() {
    sessionManager.fetchAuthToken()?.let {
      showLoading()
      val bearer = "Bearer"
      viewModel.getListCategories(token = "$bearer ${sessionManager.fetchAuthToken()}").observe(this, Observer { category ->
        hideLoading()
        if (category == null) {
          showMessage()
        } else {
          adapter.setMovies(category)
          categoryList.clear()
          categoryList.addAll(category)
        }
      })
    }
  }

  /**
   * chekc show or hide button done
   */
  private fun showOrHideButtonDone() {
      val listCategoryIsSelected = categoryList.filter { it.isSelected }
      if (listCategoryIsSelected.isNotEmpty()){
          btnDone.visibility = View.VISIBLE
      }else{
        btnDone.visibility = View.INVISIBLE
      }
  }

  /**
   * registerOnBack: back to go home
   */
  private fun registerOnBack() {
    imgBackButton.setOnClickListener {
      finish()
    }
  }
}
