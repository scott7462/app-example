package com.example.domain.repository

import com.example.domain.entities.User

interface UserRemoteDataSources {
    suspend fun fetchAllUsers(): List<User>
}