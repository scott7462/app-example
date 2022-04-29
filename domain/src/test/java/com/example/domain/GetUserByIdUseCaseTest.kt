package com.example.domain

import com.example.domain.entities.DomainError
import com.example.domain.entities.DomainResults
import com.example.domain.entities.User
import com.example.domain.repository.UserRepository
import com.example.domain.usecases.GetUsersById
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserByIdUseCaseTest {

    private val repo: UserRepository = mockk()
    private val useCase = GetUsersById(repo)

    @Test
    fun `Given get users useCase When request all users Then expected users are returned`() =
        runTest {
            val expected = DomainResults.Success(dummySuccessResponse)
            coEvery { repo.getUserById(userUid) } returns dummySuccessResponse
            val actual = useCase(userUid)
            assertEquals(expected, actual)
        }

    @Test
    fun `Given get users useCase When request all users Then expected error of not user is returned`() =
        runTest {
            val expected = DomainResults.Error(dummyExpectedError)
            coEvery { repo.getUserById(userUid) } throws dummyExpectedError
            val actual = useCase(userUid)
            assertEquals(expected, actual)
        }

    @Test
    fun `Given get users useCase When request all users Then unexpected error of not user is returned`() =
        runTest {
            val expected = DomainResults.Error(DomainError.mapError(dummyUnExpectedError))
            coEvery { repo.getUserById(userUid) } throws dummyUnExpectedError
            val actual = useCase(userUid)
            assertEquals(expected, actual)
        }

    companion object {
        const val userUid: String = "1"
        val dummySuccessResponse = User(
            uid = "1",
            avatarUrl = "",
            firstName = "Pedro",
            lastName = "Scott",
            jobDescription = "Android Developer"
        )
        val dummyUnExpectedError = IllegalAccessException()
        val dummyExpectedError = DomainError.UsersNotFound
    }
}