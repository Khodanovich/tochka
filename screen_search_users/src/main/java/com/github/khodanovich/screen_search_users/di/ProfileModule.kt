package com.github.khodanovich.screen_search_users.di

import com.github.khodanovich.screen_search_users.data.SearchUserRepositoryImpl
import com.github.khodanovich.screen_search_users.domain.SearchUserInteractor
import com.github.khodanovich.screen_search_users.domain.SearchUserInteractorImpl
import com.github.khodanovich.screen_search_users.domain.SearchUserRepository
import com.github.khodanovich.screen_search_users.presentation.SearchUsersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchUserModule = module {

    viewModel { SearchUsersViewModel(get(), get(), get()) }
    single<SearchUserRepository> { SearchUserRepositoryImpl(get()) }
    single<SearchUserInteractor> { SearchUserInteractorImpl(get()) }
}