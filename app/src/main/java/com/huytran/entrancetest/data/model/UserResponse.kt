package com.huytran.entrancetest.data.model
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)