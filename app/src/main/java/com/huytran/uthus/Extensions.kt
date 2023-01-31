/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.uthus

import android.view.View
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
  val snack = Snackbar.make(this, message, length)
  snack.show()
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_INDEFINITE, f: Snackbar.() -> Unit) {
  val snack = Snackbar.make(this, message, length)
  snack.f()
  snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
  setAction(action, listener)
  color?.let { setActionTextColor(color) }
}

//@BindingAdapter("imageUrl")
//fun ImageView.setImageUrl(url: String?) {
//  Picasso.get().load(url).into(this)
//}
//
//@BindingAdapter("imageUrl")
//fun ImageView.setImageUrl(int: Int) {
//  this.setImageDrawable(resources.getDrawable(int,null))
//}