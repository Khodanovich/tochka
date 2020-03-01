package com.github.khodanovich.screen_search_users.domain.mapping

import com.github.khodanovich.core_api.users.model.User
import com.github.khodanovich.screen_search_users.domain.model.UserEntity
import io.reactivex.Observable


internal fun Observable<List<User>>.toEntities(): Observable<List<UserEntity>>{
    return this.map { it.map { it.toUserEntity() } }
}

internal fun User.toUserEntity(): UserEntity =
        UserEntity(
            login = this.login,
            id = this.id,
            avatarUrl = this.avatarUrl)

