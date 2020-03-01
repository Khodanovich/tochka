package com.github.khodanovich.core_api.users

import com.github.khodanovich.core_api.users.model.UsersNetwork
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface UsersApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") searchQuery: String,
        @Query("page") page: Long,
        @Query("per_page") perPage: Int
    ): Observable<UsersNetwork>

}