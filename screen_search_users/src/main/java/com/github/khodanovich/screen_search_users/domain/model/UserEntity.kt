package com.github.khodanovich.screen_search_users.domain.model


internal data class UserEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String
)