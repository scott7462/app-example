package com.example.domain.usecases

import com.example.domain.entities.DomainError
import com.example.domain.entities.DomainResults
import com.example.domain.entities.User
import com.example.domain.repository.UserRepository

interface GetUserByIdUseCase {
    suspend operator fun invoke(udi: String): DomainResults<User>
}

class GetUsersById(
    private val repository: UserRepository
) : GetUserByIdUseCase {

    override suspend fun invoke(udi: String): DomainResults<User> = try {
        DomainResults.Success(repository.getUserById(udi))
    } catch (error: Exception) {
        DomainResults.Error(DomainError.mapError(error))
    }
}
