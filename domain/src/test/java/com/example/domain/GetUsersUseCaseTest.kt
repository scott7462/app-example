package com.example.domain

import com.example.domain.entities.DomainError
import com.example.domain.entities.DomainResults
import com.example.domain.entities.User
import com.example.domain.repository.UserRepository
import com.example.domain.usecases.GetUsers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetUsersUseCaseTest {

    private val repo: UserRepository = mockk()
    private val useCase = GetUsers(repo)

    @Test
    fun `Given get users useCase When request all users Then expected users are returned`() =
        runTest {
            val expected = DomainResults.Success(dummySuccessResponse)
            coEvery { repo.getAllUsers() } returns dummySuccessResponse
            val actual = useCase()
            assertEquals(expected, actual)
        }

    @Test
    fun `Given get users useCase When request all users Then expected error of not user is returned`() =
        runTest {
            val expected = DomainResults.Error(dummyExpectedError)
            coEvery { repo.getAllUsers() } throws dummyExpectedError
            val actual = useCase()
            assertEquals(expected, actual)
        }

    @Test
    fun `Given get users useCase When request all users Then unexpected error of not user is returned`() =
        runTest {
            val expected = DomainResults.Error(DomainError.mapError(dummyUnExpectedError))
            coEvery { repo.getAllUsers() } throws dummyUnExpectedError
            val actual = useCase()
            assertEquals(expected, actual)
        }

    companion object {
        val dummySuccessResponse = listOf(
            User(
                uid = "1",
                avatarUrl = "",
                firstName = "Pedro",
                lastName = "Scott",
                jobDescription = "Android Developer"
            ),
            User(
                uid = "2",
                avatarUrl = "",
                firstName = "Marsela",
                lastName = "Sardi",
                jobDescription = "IOs Developer"
            ),
            User(
                uid = "3",
                avatarUrl = "",
                firstName = "Patricia",
                lastName = "Scott",
                jobDescription = "Company Owner"
            )
        )
        val dummyUnExpectedError = IllegalAccessException()
        val dummyExpectedError = DomainError.UsersNotFound
    }
}