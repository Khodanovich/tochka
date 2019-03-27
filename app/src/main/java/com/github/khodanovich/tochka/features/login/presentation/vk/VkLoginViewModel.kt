package com.github.khodanovich.tochka.features.login.presentation.vk

import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor


class VkLoginViewModel(private val authTypeInteractor: AuthInteractor) : GlobalViewModel() {

    fun onAuthSuccess() {
        authTypeInteractor.setUpAuthTypeAsVk()
        navigate(VkLoginFragmentDirections.vkToSearchUser())
    }

    fun onAuthFail() = back()
}