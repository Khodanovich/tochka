package com.github.khodanovich.tochka.features.search.users.presentation

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.github.khodanovich.tochka.features.global.error.ErrorHandler
import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.search.users.presentation.pagination.SearchUsersDataSource
import com.github.khodanovich.tochka.features.global.event.LoginEvent
import com.github.khodanovich.tochka.features.global.event.LoginEventBus
import com.github.khodanovich.tochka.features.global.pagination.searchConfig
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import com.github.khodanovich.tochka.features.search.users.presentation.interactor.SearchUserInteractor
import com.github.khodanovich.tochka.features.search.users.presentation.pagination.LoadState
import com.github.khodanovich.tochka.util.extensions.async


class SearchUsersViewModel(private val loginEventBus: LoginEventBus,
                           private val searchUsersInteractor: SearchUserInteractor,
                           private val errorHandler: ErrorHandler
) : GlobalViewModel() {

    companion object {
        private const val PER_PAGE = 30
    }

    var usersLiveData = MutableLiveData<PagedList<UserEntity>>()
    val loadState = MutableLiveData<LoadState>()


    fun onViewCreated(){
        loginEventBus.setLoginEvent(LoginEvent.LOGIN)
    }

    fun onChangedSearchQuery(searchQuery: String) {
        val dataSource = createDataSource(searchQuery)
        val pagedList = createPagedList(dataSource)

        usersLiveData.postValue(pagedList)
    }

    private fun createDataSource(searchQuery: String) =
        SearchUsersDataSource (loadState, compositeDisposable, errorMessageLiveData, errorHandler) { page ->
            searchUsersInteractor.getUsers(searchQuery, page, PER_PAGE)
        }

    private fun createPagedList(dataSource: SearchUsersDataSource) =
        PagedList.Builder(dataSource, searchConfig)
            .async()
            .build()
}