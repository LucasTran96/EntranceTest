package com.huytran.entrancetest.data.model
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("password")
    val password: String
)