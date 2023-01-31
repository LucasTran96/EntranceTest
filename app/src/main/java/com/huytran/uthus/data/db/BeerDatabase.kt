package com.huytran.uthus.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huytran.uthus.data.model.Beer

@Database(entities = [Beer::class], version = 4, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao() : BeerDao
}