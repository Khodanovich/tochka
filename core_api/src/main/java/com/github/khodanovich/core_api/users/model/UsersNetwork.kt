package com.github.khodanovich.core_api.users.model

import com.google.gson.annotations.SerializedName

data class UsersNetwork(
    @SerializedName("total_count")
    var totalCount: Int,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var users: List<User>
)

data class User(
    var login: String,
    var id: Long,
    @SerializedName("avatar_url")
    var avatarUrl: String
)