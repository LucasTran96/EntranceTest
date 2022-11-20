/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.TextWatcher
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.huytran.entrancetest.R
import com.huytran.entrancetest.action
import com.huytran.entrancetest.data.db.SessionManager
import com.huytran.entrancetest.data.model.User
import com.huytran.entrancetest.databinding.ActivitySignupBinding
import com.huytran.entrancetest.snack
import com.huytran.entrancetest.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

  //private val toolbar: Toolbar by lazy { toolbar_toolbar_view as Toolbar }
  private lateinit var viewModel: SignUpViewModel
  private lateinit var binding: ActivitySignupBinding
  private lateinit var sessionManager: SessionManager

  private var isEmailValid = false

  //override fun getToolbarInstance(): Toolbar? = toolbar

  fun searchMovieClicked(view: View) {
//    if (titleEditText.text.toString().isNotBlank()) {
//      startActivity(intentFor<SearchMovieActivity>("title" to titleEditText.text.toString()))
//    } else {
//      showMessage(getString(R.string.enter_title))
//    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView<ActivitySignupBinding>(this, R.layout.activity_signup)
    viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)


    binding.viewModel = viewModel
    binding.cslSignUp.isEnabled = false
    binding.txtTerms.text = getTextProductRoles()
    sessionManager = SessionManager(applicationContext)
    configureLiveDataObservers()
    emailChangedListener()
    passwordChangedListener()

  }

  // email Changed Listener
  private fun emailChangedListener()
  {
    binding.textEmailField.addTextChangedListener(object : TextWatcher{

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        binding.textEmailFieldContains.helperText = validEmail()
        checkEmailAndPassword()
      }
      override fun afterTextChanged(p0: Editable?) {

      }
    })
  }

  // validEmail
  private fun validEmail(): String?
  {
    val emailText = binding.textEmailField.text.toString()
    if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
    {
      isEmailValid = false
      return "Invalid Email Address"
    }
    isEmailValid = true
    return null
  }

  //add text changed listener
  private fun passwordChangedListener()
  {

    binding.textPasswordField.addTextChangedListener(object : TextWatcher{
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        binding.textPasswordFieldContains.helperText = validPassword()
        checkEmailAndPassword()
      }
      override fun afterTextChanged(p0: Editable?) {
      }
    })
  }

  // valid password
  private fun validPassword(): String?
  {
    val passwordText = binding.textPasswordField.text.toString()
    if(passwordText.length in 6..18)
    {
      if(validEmail()?.isNullOrEmpty() == true){
        binding.cslSignUp.isEnabled = true
      }
      if(passwordText.matches(".*[A-Z].*".toRegex()))
      {
        if(passwordText.matches(".*[a-z].*".toRegex()))
        {
          if (passwordText.matches(".*[0-9].*".toRegex())){

            if (passwordText.matches(".*[@#\$%^&+=].*".toRegex())){
              return "Strong"
            }
            return "Good"
          }
          return "Fair"
        }
      }
      return "weak"
    }
    else{
      return "The password must be between 6-18 characters."
    }
  }

  // check email and password
  private fun checkEmailAndPassword()
  {
    // validPassword not empty and small than 5 characters "Strong, Good, Fair, weak"
    val validPassword = binding.textPasswordFieldContains.helperText?.length in 1..6
    binding.cslSignUp.isEnabled = isEmailValid && validPassword
  }

  private fun showMessage(msg: String) {
    addLayout.snack((msg), Snackbar.LENGTH_LONG) {
      action(getString(R.string.ok)) {
      }
    }
  }

  // configure LiveData observers
  private fun configureLiveDataObservers() {
    viewModel.getSaveLiveData().observe(this, Observer { saved ->
      saved?.let {
        if (saved) {
          val user = User(binding.textEmailField.text.toString(), getString(R.string.firstname), getString(R.string.lastname), binding.textPasswordField.text.toString())
          viewModel.signUp(user).observe(this, Observer { user ->
            if (user == null) {
              showMessage("An unknown error has occurred!")
            } else {
              sessionManager.saveAuthToken(user.token)
              sessionManager.fetchAuthToken()?.let {
                startActivityToSignUpActivity()
              }
            }
          })
        } else {
          showMessage(getString(R.string.check_policy))
        }
      }
    })
  }

  // Start activity to SignUp activity
  private fun startActivityToSignUpActivity(){
    val intent = Intent(applicationContext, CategoryActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
  }

  /**
   * getTextProductRoles: set color and onclick for terms_of_service text.
   */
  private fun getTextProductRoles(): SpannableStringBuilder {
    val builder = SpannableStringBuilder()
    builder.append(resources.getString(R.string.terms_of_service_and_privacy_policy))
    builder.setSpan(ForegroundColorSpan(resources.getColor(R.color.color_FFFFFF50)), 0, builder.length, 0)

    builder.append(" ")
    val length = builder.length
    builder.append(resources.getString(R.string.terms_of_service))
    builder.setSpan(object : ClickableSpan() {
      override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = false
      }
      override fun onClick(widget: View) {
        //onClick
      }
    }, length, builder.length, 0)
    builder.setSpan(ForegroundColorSpan(resources.getColor(R.color.color_647FFF)), length, builder.length, 0)

    builder.append(" ")
    val length2 = builder.length
    builder.append(resources.getString(R.string.and))
    builder.setSpan(ForegroundColorSpan(resources.getColor(R.color.color_FFFFFF50)), length2, builder.length, 0)

    builder.append(" ")
    val length3 = builder.length
    builder.append(resources.getString(R.string.privacy_policy))
    builder.setSpan(object : ClickableSpan() {
      override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = false
      }
      override fun onClick(widget: View) {
        // onClick
      }
    }, length3, builder.length, 0)
    builder.setSpan(ForegroundColorSpan(resources.getColor(R.color.color_647FFF)), length3, builder.length, 0)

    return builder
  }
}
