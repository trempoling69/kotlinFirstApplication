package com.example.myapplication.api

import com.example.myapplication.api.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("/posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostResponse
}