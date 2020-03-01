package com.github.khodanovich.screen_search_users.data

import com.github.khodanovich.core_api.users.UsersApiService
import com.github.khodanovich.core_api.users.model.User
import com.github.khodanovich.screen_search_users.domain.SearchUserRepository
import io.reactivex.Observable


internal class SearchUserRepositoryImpl(private val api: UsersApiService) : SearchUserRepository {

    override fun getUsers(
        searchQuery: String,
        page: Long,
        perPage: Int
    ): Observable<List<User>> {
        return api.getUsers(searchQuery, page, perPage)
            .map { it.users }
    }
}