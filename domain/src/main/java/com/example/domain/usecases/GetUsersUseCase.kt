package com.example.domain.usecases

import com.example.domain.entities.DomainError
import com.example.domain.entities.DomainResults
import com.example.domain.entities.User
import com.example.domain.repository.UserRepository

interface GetUsersUseCase {
    suspend operator fun invoke(): DomainResults<List<User>>
}

class GetUsers(
    private val repository: UserRepository
) : GetUsersUseCase {

    override suspend operator fun invoke(): DomainResults<List<User>> = try {
        DomainResults.Success(repository.getAllUsers())
    } catch (error: Exception) {
        DomainResults.Error(DomainError.mapError(error))
    }
}