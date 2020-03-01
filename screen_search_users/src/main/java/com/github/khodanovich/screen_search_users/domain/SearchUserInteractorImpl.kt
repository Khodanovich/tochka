package com.github.khodanovich.screen_search_users.domain

import com.github.khodanovich.screen_search_users.domain.mapping.toEntities
import com.github.khodanovich.screen_search_users.domain.model.UserEntity
import io.reactivex.Observable


internal class SearchUserInteractorImpl(private val repository: SearchUserRepository) :
    SearchUserInteractor {

    override fun getUsers(
        searchQuery: String,
        page: Long,
        perPage: Int
    ): Observable<List<UserEntity>> {
        return repository.getUsers(
            searchQuery = searchQuery,
            page = page,
            perPage = perPage).toEntities()
    }
}