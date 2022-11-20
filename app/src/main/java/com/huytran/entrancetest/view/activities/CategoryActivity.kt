/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.huytran.entrancetest.R
import com.huytran.entrancetest.action
import com.huytran.entrancetest.data.db.SessionManager
import com.huytran.entrancetest.snack
import com.huytran.entrancetest.view.adapters.CategoryListAdapter
import com.huytran.entrancetest.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

  private var adapter = CategoryListAdapter(mutableListOf())

  private lateinit var viewModel: CategoryViewModel

  private lateinit var title: String
  private lateinit var sessionManager: SessionManager


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_category)
    intent?.extras?.getString("title")?.let {
      title = it
    }
    viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    val layoutManager = GridLayoutManager(this, 3)
    // Optionally customize the position you want to default scroll to
    layoutManager.scrollToPosition(0);
    // Attach layout manager to the RecyclerView
    categoryRecyclerView.layoutManager = layoutManager;
    categoryRecyclerView.adapter = adapter
    sessionManager = SessionManager(applicationContext)
    searchMovie()

  }

  private fun showLoading() {
    categoryProgressBar.visibility = View.VISIBLE
    categoryRecyclerView.isEnabled = false
  }

  private fun hideLoading() {
    categoryProgressBar.visibility = View.GONE
    categoryRecyclerView.isEnabled = true
  }

  private fun showMessage() {
    categoryLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
      action(getString(R.string.ok)) {
        searchMovie()
      }
    }
  }

  private fun searchMovie() {
    sessionManager.fetchAuthToken()?.let {
      getCategories()
    }

  }

  private fun getCategories() {
    showLoading()
    viewModel.getListCategories(token = "Bearer ${sessionManager.fetchAuthToken()}").observe(this, Observer { category ->
      hideLoading()
      if (category == null) {
        Log.d("UserTesst", "category == null")
        showMessage()
      } else {
        Log.d("UserTesst", "User = ${category.toString()}")
        adapter.setMovies(category)
      }
    })

  }
}
