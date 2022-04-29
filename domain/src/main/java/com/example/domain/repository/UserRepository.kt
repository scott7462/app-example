package com.example.domain.repository

import com.example.domain.entities.User

interface UserRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(udi: String): User
}