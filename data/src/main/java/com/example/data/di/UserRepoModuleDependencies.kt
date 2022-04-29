package com.example.data.di

import com.example.data.UserRepositoryImpl
import com.example.data.local.LocalMapper
import com.example.data.local.UserLocal
import com.example.data.local.dao.UserDao
import com.example.data.remote.api.UsersApi
import com.example.data.remote.UserRemote
import com.example.domain.repository.UserLocalDataSources
import com.example.domain.repository.UserRemoteDataSources
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class UserRepoModuleDependencies {

    @Provides
    internal fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    internal fun providesRemote(
        api: UsersApi,
        dispatcher: CoroutineDispatcher,
    ): UserRemoteDataSources = UserRemote(api, dispatcher)

    @Provides
    internal fun providesLocal(
        userDao: UserDao,
        mapper: LocalMapper,
        dispatcher: CoroutineDispatcher,
    ): UserLocalDataSources = UserLocal(userDao, mapper, dispatcher)

    @Provides
    internal fun providesRepository(
        remote: UserRemoteDataSources,
        local: UserLocalDataSources,
    ): UserRepository = UserRepositoryImpl(remote, local)
}
