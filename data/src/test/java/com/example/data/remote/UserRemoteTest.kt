package com.example.data.remote

import com.example.data.remote.api.UsersApi
import com.example.data.remote.responses.UserRemoteJson
import com.example.data.remote.responses.UserResponse
import com.example.domain.entities.User
import com.example.domain.repository.UserRemoteDataSources
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class UserRemoteTest {

    private val api: UsersApi = mockk()

    private val remote: UserRemoteDataSources = UserRemote(
        api = api,
        dispatcher = Dispatchers.IO
    )

    @Test
    fun `Given success remote response and local When all users are requested Then result is success`() =
        runTest {
            val expected = dummyResult
            coEvery { api.getUsers() } returns dummyResponse

            val actual = remote.fetchAllUsers()
            assertEquals(expected, actual)
        }

    companion object {

        val dummyResponse = UserResponse(
            users = listOf(
                UserRemoteJson(
                    id = "1",
                    avatarUrl = "",
                    firstName = "Pedro",
                    lastName = "Scott",
                    jobDescription = "Android Developer"
                ),
                UserRemoteJson(
                    id = "2",
                    avatarUrl = "",
                    firstName = "Marsela",
                    lastName = "Sardi",
                    jobDescription = "IOs Developer"
                ),
                UserRemoteJson(
                    id = "3",
                    avatarUrl = "",
                    firstName = "Patricia",
                    lastName = "Scott",
                    jobDescription = "Company Owner"
                )
            )
        )

        val dummyResult = listOf(
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