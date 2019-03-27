package com.github.khodanovich.tochka.features.search.users.domain.interactor

import com.github.khodanovich.tochka.features.search.users.data.model.User
import io.reactivex.Observable


interface SearchUserRepository {

    fun getUsers(searchQuery: String, page: Long, perPage: Int) : Observable<List<User>>

}