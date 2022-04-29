package com.example.pedroscott.di

import com.example.domain.repository.UserRepository
import com.example.domain.usecases.GetUserByIdUseCase
import com.example.domain.usecases.GetUsersUseCase
import com.example.domain.usecases.GetUsers
import com.example.domain.usecases.GetUsersById
import com.example.pedroscott.features.UiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
open class MainModule {

    @Provides
    fun uiMapper() = UiMapper

    @Provides
    fun providesGetUsers(
        repo: UserRepository
    ): GetUsersUseCase = GetUsers(repo)

    @Provides
    fun providesGetUserById(
        repo: UserRepository
    ): GetUserByIdUseCase = GetUsersById(repo)
}