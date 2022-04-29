package com.example.pedroscott.features.main.viewmodel

import com.example.domain.entities.DomainError
import com.example.domain.entities.DomainResults
import com.example.domain.entities.User
import com.example.domain.usecases.GetUsersUseCase
import com.example.pedroscott.features.UiMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val getUserUserCase: GetUsersUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainViewModel(
            mapper = UiMapper,
            getUsersUseCase = getUserUserCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given success use cases response When user request users Then list of user is displayed`() = runTest {
        coEvery { getUserUserCase.invoke() } returns dummySuccess
        mainViewModel.loadData()
        assertTrue(mainViewModel.uiState is MainViewSate.Success)
    }

    @Test
    fun `Given error When user request users Then errors is displayed`() = runTest {
        coEvery { getUserUserCase.invoke() } returns DomainResults.Error(DomainError.UsersNotFound)
        mainViewModel.loadData()
        assertTrue(mainViewModel.uiState is MainViewSate.Error)
    }

    @Test
    fun `Given a throw error When user request users Then errors is displayed`() = runTest {
        coEvery { getUserUserCase.invoke() } throws IllegalAccessError()
        mainViewModel.loadData()
        assertTrue(mainViewModel.uiState is MainViewSate.Error)
    }

    @Test
    fun `Given no actions When user open the screen Then loading is displayed`() = runTest {
        assertTrue(mainViewModel.uiState is MainViewSate.Loading)
    }


    companion object {
        val dummySuccess = DomainResults.Success(
            listOf(
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
        )
    }
}