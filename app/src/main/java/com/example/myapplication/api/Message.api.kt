package com.example.myapplication.api

import com.example.myapplication.api.model.MessageResponse
import retrofit2.http.GET

interface MessageApi {
    @GET("/endpoint")
    suspend fun getPosts(): List<MessageResponse>
}