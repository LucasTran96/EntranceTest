package com.huytran.entrancetest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huytran.entrancetest.data.model.Beer

@Database(entities = [Beer::class], version = 3, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao() : BeerDao
}