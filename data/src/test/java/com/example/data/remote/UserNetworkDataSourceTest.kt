package com.example.data.remote

import com.example.data.remote.api.UsersApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class UserNetworkDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .build()
        .create(UsersApi::class.java)

    private val remote = UserRemote(api, Dispatchers.IO)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    //Here we can add some integration test with mock responses

}