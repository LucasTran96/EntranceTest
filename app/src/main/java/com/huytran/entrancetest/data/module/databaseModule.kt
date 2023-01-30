package com.huytran.entrancetest.data.module

import android.content.Context
import androidx.room.Room
import com.huytran.entrancetest.data.db.BeerDatabase
import com.huytran.entrancetest.viewmodel.ChildFragmentViewModel
import org.koin.dsl.module

var databaseModule = module{

    fun provideDatabase(context: Context): BeerDatabase {
        return Room.databaseBuilder(context, BeerDatabase::class.java, "beer-db").build()
    }
    single { provideDatabase(get())}
}