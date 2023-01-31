package com.huytran.uthus.data.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BeerResponse(
    @SerializedName("data")
    @Expose
    val data: List<Data>?,
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

data class Data(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("price")
    @Expose
    val price: String?,
    @SerializedName("rating")
    @Expose
    val rating: Rating?,
    @SerializedName("isFavorite")
    @Expose
    var isFavorite: Boolean = false,
    @SerializedName("note")
    @Expose
    var note: String = ""
)

data class Rating(
    @SerializedName("average")
    @Expose
    val average: Double?,
    @SerializedName("reviews")
    @Expose
    val reviews: Int?
)