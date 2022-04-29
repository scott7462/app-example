package com.example.data.local

import com.example.data.local.dao.UserTable
import com.example.domain.entities.User


internal class LocalUserMapper : LocalMapper {

    override fun mapUserToLocal(user: User): UserTable = UserTable(
        uid = user.uid,
        avatarUrl = user.avatarUrl,
        firstName = user.firstName,
        lastName = user.lastName,
        jobDescription = user.jobDescription
    )


    override fun mapLocalToUser(table: UserTable): User = User(
        uid = table.uid,
        avatarUrl = table.avatarUrl,
        firstName = table.firstName,
        lastName = table.lastName,
        jobDescription = table.jobDescription
    )
}

internal interface LocalMapper {
    fun mapUserToLocal(user: User): UserTable
    fun mapLocalToUser(table: UserTable): User
}