package com.huytran.entrancetest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Beer")
data class Beer(
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    val image: String?,
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    val name: String?,
    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")
    val price: String?,
    @SerializedName("isFavorite")
    @Expose
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
    @SerializedName("note")
    @Expose
    @ColumnInfo(name = "note")
    var note: String? = ""
)
