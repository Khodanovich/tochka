package com.github.khodanovich.tochka.features.search.users.domain.model


data class UserEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String
)