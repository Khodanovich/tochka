package com.github.khodanovich.tochka.features.login.presentation.google

import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor


class GoogleLoginViewModel(private val authTypeInteractor: AuthInteractor) : GlobalViewModel() {

    fun onAuthSuccess() {
        authTypeInteractor.setUpAuthTypeAsGoogle()
        navigate(GoogleLoginFragmentDirections.googleToSearchUser())
    }

    fun onAuthFail() = back()
}