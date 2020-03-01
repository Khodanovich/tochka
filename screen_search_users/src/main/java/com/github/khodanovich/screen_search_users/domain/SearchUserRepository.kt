package com.github.khodanovich.screen_search_users.domain

import com.github.khodanovich.core_api.users.model.User
import io.reactivex.Observable


internal interface SearchUserRepository {

    fun getUsers(searchQuery: String, page: Long, perPage: Int) : Observable<List<User>>

}