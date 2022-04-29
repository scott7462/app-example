package com.example.data.remote.api

import com.example.data.remote.responses.UserResponse
import retrofit2.http.GET

internal interface UsersApi {
    @GET("/users")
    suspend fun getUsers(): UserResponse
}