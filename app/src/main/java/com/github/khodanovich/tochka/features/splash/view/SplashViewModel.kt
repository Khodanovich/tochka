package com.github.khodanovich.tochka.features.splash.view

import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.global.system.preferences.SharedPreferences
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashViewModel(private val preferences: SharedPreferences) : GlobalViewModel() {

    companion object {
        private const val TIMEOUT = 2000L
    }

    fun onViewCreated() {
        Observable.timer(TIMEOUT, TimeUnit.MILLISECONDS, Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onTimeoutEnded() }
            .onClearedUnsubscribe()
    }

    private fun onTimeoutEnded(){
        if (preferences.isAuthenticated) navigate(SplashFragmentDirections.toSearchUser())
        else navigate(SplashFragmentDirections.toLogin())
    }
}