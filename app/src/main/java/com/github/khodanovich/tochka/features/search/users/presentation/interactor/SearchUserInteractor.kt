package com.github.khodanovich.tochka.features.search.users.presentation.interactor

import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import io.reactivex.Observable


interface SearchUserInteractor {

    fun getUsers(searchQuery: String, page: Long, perPage: Int): Observable<List<UserEntity>>

}