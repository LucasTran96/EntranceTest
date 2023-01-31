package com.huytran.uthus.data.module

import android.content.Context
import androidx.room.Room
import com.huytran.uthus.data.db.BeerDatabase
import org.koin.dsl.module

var databaseModule = module{

    fun provideDatabase(context: Context): BeerDatabase {
        return Room.databaseBuilder(context, BeerDatabase::class.java, "beer-db").build()
    }
    single { provideDatabase(get())}
}