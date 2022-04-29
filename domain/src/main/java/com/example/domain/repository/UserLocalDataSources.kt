package com.example.domain.repository

import com.example.domain.entities.User

interface UserLocalDataSources {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(uid: String): User
    suspend fun saveUsers(users: List<User>)
}