package com.github.khodanovich.screen_auth.presentation.google

import com.github.khodanovich.ft_auth.domain.AuthInteractor
import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.router.Router
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel


internal class GoogleLoginViewModel(
    private val authTypeInteractor: AuthInteractor,
    private val globalDirections: GlobalDirections,
    router: Router
) : GlobalViewModel(), Router by router {

    fun onAuthSuccess() {
        authTypeInteractor.setUpAuthTypeAsGoogle()
        navigateToGlobal(globalDirections.toSearchUserWithPopUpToAuth())
    }

    fun onAuthFail() = back()
}