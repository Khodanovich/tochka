package com.github.khodanovich.tochka.features.login.presentation.facebook

import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.global.event.LoginEvent
import com.github.khodanovich.tochka.features.global.event.LoginEventBus
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor


class FacebookLoginViewModel(private val authTypeInteractor: AuthInteractor,
                             private val loginEventBus: LoginEventBus) : GlobalViewModel() {

    fun onAuthSuccess() {
        authTypeInteractor.setUpAuthTypeAsFaceBook()
        loginEventBus.setLoginEvent(LoginEvent.LOGIN)
        navigate(FacebookLoginFragmentDirections.facebookToSearchUser())
    }

    fun onAuthFail() = back()
}