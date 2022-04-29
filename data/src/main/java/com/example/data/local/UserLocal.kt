package com.example.data.local

import com.example.data.local.dao.UserDao
import com.example.domain.entities.DomainError
import com.example.domain.entities.User
import com.example.domain.repository.UserLocalDataSources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserLocal @Inject constructor(
    private val userDao: UserDao,
    private val mapper: LocalMapper,
    private val dispatcher: CoroutineDispatcher,
) : UserLocalDataSources {

    override suspend fun getUsers(): List<User> = withContext(dispatcher) {
        return@withContext safeLocalCall {
            userDao.getAll().map {
                mapper.mapLocalToUser(it)
            }
        }
    }

    override suspend fun getUserById(uid: String): User = withContext(dispatcher) {
        return@withContext safeLocalCall {
            val localUser = userDao.getUserById(uid = uid)
            mapper.mapLocalToUser(localUser)
        }
    }

    override suspend fun saveUsers(users: List<User>): Unit = withContext(dispatcher) {
        return@withContext safeLocalCall {
            val localUsers = users.map { user ->
                mapper.mapUserToLocal(user)
            }
            userDao.insertAll(*localUsers.toTypedArray())
        }
    }
}
suspend fun <T> safeLocalCall(action: suspend () -> T) = try {
    action.invoke()
} catch (error: Exception) {
    throw DomainError.LocalTransactionError(error.message ?: "", error.cause)
}