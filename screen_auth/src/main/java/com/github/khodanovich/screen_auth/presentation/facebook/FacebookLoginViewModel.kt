package com.github.khodanovich.screen_auth.presentation.facebook

import com.github.khodanovich.ft_auth.domain.AuthInteractor
import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.router.Router
import com.github_khodanovich.tochka.core_ui.event.LoginEvent
import com.github_khodanovich.tochka.core_ui.event.LoginEventBus
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel


internal class FacebookLoginViewModel(
    private val authTypeInteractor: AuthInteractor,
    private val loginEventBus: LoginEventBus,
    private val globalDirections: GlobalDirections,
    router: Router
) : GlobalViewModel(), Router by router {

    fun onAuthSuccess() {
        authTypeInteractor.setUpAuthTypeAsFaceBook()
        loginEventBus.setLoginEvent(LoginEvent.LOGIN)
        navigateToGlobal(globalDirections.toSearchUserWithPopUpToAuth())
    }

    fun onAuthFail() = back()
}