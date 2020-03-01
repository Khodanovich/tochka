package com.github.khodanovich.screen_splash.presentation

import com.github.khodanovich.tochka.core_util.preference.SharedPreferences
import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.router.Router
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


internal class SplashViewModel(
    private val preferences: SharedPreferences,
    private val globalDirections: GlobalDirections,
    router: Router
) : GlobalViewModel(), Router by router {

    companion object {
        private const val TIMEOUT = 2000L
    }

    fun onViewCreated() {
        Observable.timer(TIMEOUT, TimeUnit.MILLISECONDS, Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onTimeoutEnded() }
            .onClearedUnsubscribe()
    }

    private fun onTimeoutEnded() {
        if (preferences.isAuthenticated) navigateToGlobal(globalDirections.toSearchUser())
        else navigateToGlobal(globalDirections.toLogin())
    }
}