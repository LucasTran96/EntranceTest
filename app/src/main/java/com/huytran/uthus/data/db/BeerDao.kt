package com.huytran.uthus.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.huytran.uthus.data.model.Beer
import androidx.lifecycle.LiveData

@Dao interface BeerDao {

    @Query("select * from beer")
    fun getAllBeer() : LiveData<List<Beer>?>

    // fun getAllBeer() : Flowable<List<Beer>>
    //: LiveData<List<Beer>?>
    @Query("select * from beer where id = :beerID")
    fun getBeerByID(beerID: Int) : Beer

    @Insert(onConflict = REPLACE)
    fun insertBeer(beer:Beer)

    @Update(onConflict = REPLACE)
    fun updateBeer(beer: Beer)

    @Delete
    fun beerDelete(beer: Beer)


}