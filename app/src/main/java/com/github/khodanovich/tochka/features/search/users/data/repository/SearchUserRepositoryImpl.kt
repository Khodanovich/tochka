package com.github.khodanovich.tochka.features.search.users.data.repository

import com.github.khodanovich.tochka.api.ApiService
import com.github.khodanovich.tochka.features.search.users.data.model.User
import com.github.khodanovich.tochka.features.search.users.domain.interactor.SearchUserRepository
import io.reactivex.Observable


class SearchUserRepositoryImpl(private val api: ApiService) : SearchUserRepository {

    override fun getUsers(
        searchQuery: String,
        page: Long,
        perPage: Int
    ): Observable<List<User>> {
        return api.getUsers(searchQuery, page, perPage)
            .map { it.users }
    }
}