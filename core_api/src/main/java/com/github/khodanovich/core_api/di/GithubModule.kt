package com.github.khodanovich.core_api.di

import com.github.khodanovich.core_api.BuildConfig.ENDPOINT
import com.github.khodanovich.core_api.users.UsersApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    single { makeGitHubService() }
}

fun makeGitHubService(): UsersApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(UsersApiService::class.java)
}