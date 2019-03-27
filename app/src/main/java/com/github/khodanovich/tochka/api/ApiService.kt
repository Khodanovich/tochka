package com.github.khodanovich.tochka.api

import com.github.khodanovich.tochka.features.search.users.data.model.UsersNetwork
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") searchQuery: String,
        @Query("page") page: Long,
        @Query("per_page") perPage: Int
    ): Observable<UsersNetwork>

}