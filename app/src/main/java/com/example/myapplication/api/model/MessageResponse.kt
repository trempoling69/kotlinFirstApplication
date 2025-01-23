package com.example.myapplication.api.model

import com.google.gson.annotations.SerializedName

data class MessageResponse (
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int
)

