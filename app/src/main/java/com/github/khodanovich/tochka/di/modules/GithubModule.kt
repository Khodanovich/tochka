package com.github.khodanovich.tochka.di.modules

import com.github.khodanovich.tochka.BuildConfig.ENDPOINT
import com.github.khodanovich.tochka.api.ApiService
import com.github.khodanovich.tochka.features.search.users.domain.interactor.SearchUserInteractorImpl
import com.github.khodanovich.tochka.features.search.users.data.repository.SearchUserRepositoryImpl
import com.github.khodanovich.tochka.features.search.users.domain.interactor.SearchUserRepository
import com.github.khodanovich.tochka.features.search.users.presentation.SearchUsersViewModel
import com.github.khodanovich.tochka.features.search.users.presentation.interactor.SearchUserInteractor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val githubModule = module {
    viewModel { SearchUsersViewModel(get(), get(), get()) }

    single { makeGitHubService() }

    single<SearchUserRepository> { SearchUserRepositoryImpl(get()) }
    single<SearchUserInteractor> { SearchUserInteractorImpl(get()) }
}

fun makeGitHubService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}