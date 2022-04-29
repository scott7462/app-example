package com.example.data.local

import com.example.data.local.dao.UserDao
import com.example.data.local.dao.UserTable
import com.example.domain.entities.User
import com.example.domain.repository.UserLocalDataSources
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class UserLocalTest {

    private val dao: UserDao = mockk()

    private val local: UserLocalDataSources = UserLocal(
        userDao = dao,
        mapper = LocalUserMapper(),
        dispatcher = Dispatchers.IO
    )

    @Test
    fun `Given success transactions When all users are requested Then result is success`() =
        runTest {
            val expected = dummyResult
            coEvery { dao.getAll() } returns dummyTables
            val actual = local.getUsers()
            assertEquals(expected, actual)
        }


    companion object {
        val dummyTables = listOf(
            UserTable(
                uid = "1",
                avatarUrl = "",
                firstName = "Pedro",
                lastName = "Scott",
                jobDescription = "Android Developer"
            ),
            UserTable(
                uid = "2",
                avatarUrl = "",
                firstName = "Marsela",
                lastName = "Sardi",
                jobDescription = "IOs Developer"
            ),
            UserTable(
                uid = "3",
                avatarUrl = "",
                firstName = "Patricia",
                lastName = "Scott",
                jobDescription = "Company Owner"
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