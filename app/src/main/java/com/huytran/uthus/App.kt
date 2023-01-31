/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.uthus

import android.app.Application
import com.huytran.uthus.data.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
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
    startKoin{
      androidLogger()
      androidContext(this@App)
      modules(listOf(repositoryModule,ChildFragmentViewModelModule, retrofitModule, apiModule, databaseModule))
    }

    INSTANCE = this
    this.initCicerone()


  }

  private fun initCicerone() {
    this.cicerone = Cicerone.create()
  }
}