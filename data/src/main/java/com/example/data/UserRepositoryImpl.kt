package com.example.data

import com.example.domain.entities.DomainError
import com.example.domain.entities.User
import com.example.domain.entities.safeCall
import com.example.domain.repository.UserLocalDataSources
import com.example.domain.repository.UserRemoteDataSources
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val remote: UserRemoteDataSources,
    private val local: UserLocalDataSources,
) : UserRepository {

    override suspend fun getAllUsers(): List<User> = safeCall {
        try {
            val remoteResponse = remote.fetchAllUsers()
            local.saveUsers(remoteResponse)
            local.getUsers()
        } catch (error: DomainError.ServerConnectionError) {
            local.getUsers()
        }
    }

    override suspend fun getUserById(udi: String): User =
        safeCall { local.getUserById(uid = udi) }
}