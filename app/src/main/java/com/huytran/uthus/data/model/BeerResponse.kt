package com.huytran.uthus.data.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BeerResponse(
    @SerializedName("data")
    @Expose
    val data: List<Beer>?,
    @SerializedName("loadMore")
    @Expose
    val loadMore: Boolean?,
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("total")
    @Expose
    val total: Int?
)

//@Entity(tableName = "Beer")
//data class Data(
//    @SerializedName("id")
//    @Expose
//    @ColumnInfo(name = "id")
//    val id: Int?,
//    @SerializedName("image")
//    @Expose
//    @ColumnInfo(name = "image")
//    val image: String?,
//    @SerializedName("name")
//    @Expose
//    @ColumnInfo(name = "name")
//    val name: String?,
//    @SerializedName("price")
//    @Expose
//    @ColumnInfo(name = "price")
//    val price: String?,
//    @SerializedName("rating")
//    @Expose
//    val rating: Rating?,
//    @SerializedName("isFavorite")
//    @Expose
//    @ColumnInfo(name = "isFavorite")
//    var isFavorite: Boolean = false,
//    @SerializedName("note")
//    @Expose
//    var note: String = ""
//)

data class Rating(
    @SerializedName("average")
    @Expose
    val average: Double?,
    @SerializedName("reviews")
    @Expose
    val reviews: Int?
)