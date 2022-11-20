/*
 * Copyright by tranquochuyse@gmail.com
 */

package com.huytran.entrancetest.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreIdConverter {

  val gson = Gson()

  @TypeConverter
  fun stringToGenreList(data: String?): List<Int> {
    if (data == null) {
      return Collections.emptyList()
    }
    val listType = object : TypeToken<List<Int>>() {}.type
    return gson.fromJson(data, listType)
  }

  @TypeConverter
  fun genreListToString(genreIds: List<Int>?): String {
    if (genreIds == null) {
      return gson.toJson(Collections.emptyList<Int>())
    }
    return gson.toJson(genreIds)
  }
}