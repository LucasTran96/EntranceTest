/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class App : Application() {

  companion object {
    lateinit var INSTANCE: App
  }

  init {
    INSTANCE = this
  }

  lateinit var cicerone: Cicerone<Router>

  override fun onCreate() {
    super.onCreate()
    INSTANCE = this
    this.initCicerone()
  }

  private fun initCicerone() {
    this.cicerone = Cicerone.create()
  }
}