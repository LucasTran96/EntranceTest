package com.huytran.entrancetest.data.model
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CategoryResponse(
    @SerializedName("categories")
    @Expose
    val categories: List<Category>? = null,
    @SerializedName("totalCount")
    @Expose
    val totalCount: Int
)

data class Category(
    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    var isSelected: Boolean = false
)