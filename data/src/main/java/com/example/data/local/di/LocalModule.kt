package com.example.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.LocalMapper
import com.example.data.local.LocalUserMapper
import com.example.data.local.database.AppDatabase
import com.example.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    internal fun provideDataBase(
        @ApplicationContext appContext: Context,
    ): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "users-database"
    ).build()

    @Provides
    internal fun provideDao(
        db: AppDatabase
    ): UserDao = db.userDao()

    @Provides
    internal fun provideMapper(): LocalMapper = LocalUserMapper()
}
