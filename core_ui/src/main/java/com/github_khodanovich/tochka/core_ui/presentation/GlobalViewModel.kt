package com.github_khodanovich.tochka.core_ui.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.github.khodanovich.tochka.core_util.life_data.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class GlobalViewModel : ViewModel() {

    //TODO вытащить в отдельную сущность
    val errorMessageLiveData = SingleLiveEvent<String>()

    protected val compositeDisposable by lazy { CompositeDisposable() }

    protected fun Disposable.onClearedUnsubscribe() =
        compositeDisposable.add(this)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}