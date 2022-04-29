package com.example.data.remote

import com.example.data.remote.api.UsersApi
import com.example.domain.entities.DomainError
import com.example.domain.entities.User
import com.example.domain.entities.safeCall
import com.example.domain.repository.UserRemoteDataSources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserRemote @Inject constructor(
    private val api: UsersApi,
    private val dispatcher: CoroutineDispatcher,
) : UserRemoteDataSources {

    override suspend fun fetchAllUsers(): List<User> = withContext(dispatcher) {
        delay(3_000)
        return@withContext safeCall {
            listOf(
                User(
                    uid = "1",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2021/02/corporate-avatars-TN-1.jpg",
                    firstName = "Patricia",
                    lastName = "Scott",
                    jobDescription = "Company Owner"
                ),
                User(
                    uid = "2",
                    avatarUrl = "https://basaschools.co.za/wp-content/uploads/2021/04/boy-avator.png",
                    firstName = "Pedro",
                    lastName = "Scott",
                    jobDescription = "Android Developer"
                ),
                User(
                    uid = "3",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2016/09/avatar1a.jpg",
                    firstName = "Marsela",
                    lastName = "Sardi",
                    jobDescription = "IOs Developer"
                ),
                User(
                    uid = "4",
                    avatarUrl = "https://t4.ftcdn.net/jpg/02/79/66/93/360_F_279669366_Lk12QalYQKMczLEa4ySjhaLtx1M2u7e6.jpg",
                    firstName = "Camila",
                    lastName = "Diaz",
                    jobDescription = "Android Developer"
                ),
                User(
                    uid = "5",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2016/09/avatar1b.jpg",
                    firstName = "Jhon",
                    lastName = "Doe",
                    jobDescription = "IOs Developer"
                ),
                User(
                    uid = "6",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2021/02/avatar3-1.jpg",
                    firstName = "Santiago",
                    lastName = "Villar",
                    jobDescription = "Android Developer"
                ),
                User(
                    uid = "8",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2021/02/avatar5-1.jpg",
                    firstName = "Jenny",
                    lastName = "Cool",
                    jobDescription = "Android Developer"
                ),
                User(
                    uid = "8",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2021/02/avatar6-1.jpg",
                    firstName = "Frank",
                    lastName = "Lanzo",
                    jobDescription = "IOs Developer"
                ),
                User(
                    uid = "9",
                    avatarUrl = "https://avatars.design/wp-content/uploads/2021/02/avatar1-1.jpg",
                    firstName = "Megan",
                    lastName = "Fox",
                    jobDescription = "Android Developer"
                )
            )
    }

        //This code can be use to request real API
//        try {
//            api.getUsers().users.map { userRemote ->
//                User(
//                    uid = userRemote.id,
//                    avatarUrl = userRemote.avatarUrl,
//                    firstName = userRemote.firstName,
//                    lastName = userRemote.lastName,
//                    jobDescription = userRemote.jobDescription
//                )
//            }
//        } catch (error: Exception) {
//            throw DomainError.ServerConnectionError(error.message?: "",error.cause ?: Throwable() )
//        }
    }
}
