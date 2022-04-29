package com.example.data

import com.example.domain.entities.DomainError
import com.example.domain.entities.User
import com.example.domain.repository.UserLocalDataSources
import com.example.domain.repository.UserRemoteDataSources
import com.example.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class UserRepositoryImplTest {

    private val remote: UserRemoteDataSources = mockk()
    private val local: UserLocalDataSources = mockk(relaxed = true)

    private val repo: UserRepository = UserRepositoryImpl(
        remote = remote,
        local = local
    )

    @Test
    fun `Given success remote response and local When all users are requested Then result is success`() =
        runTest {
            val expected = dummySuccess
            coEvery { remote.fetchAllUsers() } returns expected
            coEvery { local.getUsers() } returns expected

            val actual = repo.getAllUsers()
            assertEquals(expected, actual)
        }

    @Test
    fun `Given fail remote response and success local When all users are requested Then result is success`() =
        runTest {
            val expected = dummySuccess
            coEvery { remote.fetchAllUsers() } throws DomainError.ServerConnectionError("", null)
            coEvery { local.getUsers() } returns expected

            val actual = repo.getAllUsers()
            assertEquals(expected, actual)
        }

    @Test(expected = DomainError.LocalTransactionError::class)
    fun `Given fail remote response and fail local When all users are requested Then result an Error`() =
        runTest {
            coEvery { remote.fetchAllUsers() } throws DomainError.ServerConnectionError("", null)
            coEvery { local.getUsers() } throws DomainError.LocalTransactionError("", null)
            repo.getAllUsers()
        }

    companion object {
        val dummySuccess = listOf(
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
    }
}