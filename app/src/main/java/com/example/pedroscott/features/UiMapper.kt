package com.example.pedroscott.features

import com.example.domain.entities.User

object UiMapper {

    fun mapItems(list: List<User>) = list.map {
        mapItem(it)
    }

    fun mapItem(item: User) = UserUiModel(
        uid = item.uid,
        avatarUrl = item.avatarUrl,
        firstName = item.firstName,
        lastName = item.lastName,
        jobDescription = item.jobDescription,
    )
}