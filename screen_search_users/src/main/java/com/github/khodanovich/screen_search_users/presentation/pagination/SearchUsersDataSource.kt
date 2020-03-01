package com.github.khodanovich.screen_search_users.presentation.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.khodanovich.screen_search_users.domain.model.UserEntity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


internal class SearchUsersDataSource(
    private val loadState: MutableLiveData<LoadState>,
    private val compositeDisposable: CompositeDisposable,
    private val errorMessageLiveData: MutableLiveData<String>,
    private val errorHandler: com.github_khodanovich.tochka.core_ui.error_handler.ErrorHandler,
    private val loadData: (page: Long) -> Observable<List<UserEntity>>
) : PageKeyedDataSource<Long, UserEntity>() {

    companion object {
        private const val FIRST_PAGE = 1L
        private const val SECOND_PAGE = 2L
    }

    private fun Disposable.onClearedUnsubscribe() {
        compositeDisposable.add(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, UserEntity>
    ) {
        loadData.invoke(FIRST_PAGE)
            .doOnSubscribe { loadState.postValue(LoadState.LOADING) }
            .subscribe({ onDispatchInitialResult(callback, it) }, ::onError)
            .onClearedUnsubscribe()

    }

    private fun onDispatchInitialResult(
        callback: LoadInitialCallback<Long, UserEntity>,
        users: List<UserEntity>
    ) {
        loadState.postValue(LoadState.LOADED)
        callback.onResult(users, null,
            SECOND_PAGE
        )
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, UserEntity>
    ) {
        loadData.invoke(params.key)
            .doOnSubscribe { loadState.postValue(LoadState.LOADING) }
            .subscribe({ onDispatchAfterResult(callback, it, params) }, ::onError)
            .onClearedUnsubscribe()

    }

    private fun onDispatchAfterResult(
        callback: LoadCallback<Long, UserEntity>,
        users: List<UserEntity>,
        params: LoadParams<Long>
    ) {
        loadState.postValue(LoadState.LOADED)
        callback.onResult(users, params.key + 1)
    }

    private fun onError(throwable: Throwable) {
        loadState.postValue(LoadState.error(throwable.message))
        errorHandler.proceed(errorMessageLiveData, throwable)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, UserEntity>) {}

}