package com.github.khodanovich.tochka.features.search.users.domain.mapping

import com.github.khodanovich.tochka.features.search.users.data.model.User
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import io.reactivex.Observable


fun Observable<List<User>>.toEntities(): Observable<List<UserEntity>>{
    return this.map { it.map { it.toUserEntity() } }
}

fun User.toUserEntity(): UserEntity =
        UserEntity(
            login = this.login,
            id = this.id,
            avatarUrl = this.avatarUrl)

