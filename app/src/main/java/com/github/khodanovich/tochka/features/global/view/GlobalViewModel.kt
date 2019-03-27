package com.github.khodanovich.tochka.features.global.view

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.github.khodanovich.tochka.features.global.livedata.SingleLiveEvent
import com.github.khodanovich.tochka.features.global.navigation.NavigationCommand
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class GlobalViewModel : ViewModel() {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    val errorMessageLiveData = SingleLiveEvent<String>()

    protected val compositeDisposable by lazy { CompositeDisposable() }

    protected fun navigate(directions: NavDirections) =
        navigationCommands.postValue(NavigationCommand.To(directions))

    protected fun back() =
        navigationCommands.postValue(NavigationCommand.Back)

    protected fun backTo(destinationId: Int, inclusive: Boolean = false) =
        navigationCommands.postValue(NavigationCommand.BackTo(destinationId, inclusive))

    protected fun Disposable.onClearedUnsubscribe() =
        compositeDisposable.add(this)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}