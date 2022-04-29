package com.example.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.local.dao.UserDao
import com.example.data.local.dao.UserTable
import com.example.data.local.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class UserDatabaseTest {

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    private lateinit var local: UserLocal

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
        local = UserLocal(
            userDao = userDao,
            mapper = LocalUserMapper(),
            dispatcher = Dispatchers.IO
        )
    }

    @After
    fun closeDb() {
        db.close()
    }

    //Here we can add some data base integration Test

}