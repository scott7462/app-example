package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface UserDao {

    @Query("SELECT * FROM userTable")
    fun getAll(): List<UserTable>

    @Query("SELECT * FROM userTable WHERE uid=:uid")
    fun getUserById(uid: String): UserTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserTable)
}