package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.UserDao
import com.example.data.local.dao.UserTable

@Database(entities = [UserTable::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}