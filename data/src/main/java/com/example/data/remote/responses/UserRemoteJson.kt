package com.example.data.remote.responses

import com.google.gson.annotations.SerializedName

internal data class UserRemoteJson(
    @SerializedName("id")
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("job_description")
    val jobDescription: String,
)
