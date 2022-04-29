package com.example.data.remote.responses

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("users") val users: List<UserRemoteJson>
)