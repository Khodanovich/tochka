package com.github.khodanovich.tochka.features.search.users.domain.interactor

import com.github.khodanovich.tochka.features.search.users.domain.mapping.toEntities
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import com.github.khodanovich.tochka.features.search.users.presentation.interactor.SearchUserInteractor
import io.reactivex.Observable


class SearchUserInteractorImpl(private val repository: SearchUserRepository) : SearchUserInteractor {

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