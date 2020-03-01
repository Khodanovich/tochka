package com.github.khodanovich.screen_search_users.presentation

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.github.khodanovich.screen_search_users.domain.SearchUserInteractor
import com.github.khodanovich.screen_search_users.domain.model.UserEntity
import com.github.khodanovich.screen_search_users.presentation.pagination.LoadState
import com.github.khodanovich.screen_search_users.presentation.pagination.SearchUsersDataSource
import com.github.khodanovich.tochka.core_util.extentions.async
import com.github_khodanovich.tochka.core_ui.pagination.searchConfig
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel

internal class SearchUsersViewModel(
    private val loginEventBus: com.github_khodanovich.tochka.core_ui.event.LoginEventBus,
    private val searchUsersInteractor: SearchUserInteractor,
    private val errorHandler: com.github_khodanovich.tochka.core_ui.error_handler.ErrorHandler
) : GlobalViewModel() {

    companion object {
        private const val PER_PAGE = 30
    }

    var usersLiveData = MutableLiveData<PagedList<UserEntity>>()
    val loadState = MutableLiveData<LoadState>()


    fun onViewCreated() {
        loginEventBus.setLoginEvent(com.github_khodanovich.tochka.core_ui.event.LoginEvent.LOGIN)
    }

    fun onChangedSearchQuery(searchQuery: String) {
        val dataSource = createDataSource(searchQuery)
        val pagedList = createPagedList(dataSource)

        usersLiveData.postValue(pagedList)
    }

    private fun createDataSource(searchQuery: String) =
        SearchUsersDataSource(
            loadState,
            compositeDisposable,
            errorMessageLiveData,
            errorHandler
        ) { page ->
            searchUsersInteractor.getUsers(
                searchQuery, page,
                PER_PAGE
            )
        }

    private fun createPagedList(dataSource: SearchUsersDataSource) =
        PagedList.Builder(dataSource, searchConfig)
            .async()
            .build()
}