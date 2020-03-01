package com.github.khodanovich.screen_search_users.domain

import com.github.khodanovich.screen_search_users.domain.model.UserEntity
import io.reactivex.Observable


internal interface SearchUserInteractor {

    fun getUsers(
        searchQuery: String,
        page: Long,
        perPage: Int
    ): Observable<List<UserEntity>>

}