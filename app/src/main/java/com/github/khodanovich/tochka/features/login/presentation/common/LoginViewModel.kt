package com.github.khodanovich.tochka.features.login.presentation.common

import com.github.khodanovich.tochka.features.global.view.GlobalViewModel


class LoginViewModel : GlobalViewModel(){

    fun onVkButtonClicked() = navigate(LoginFragmentDirections.toVkLogin())

    fun onGoogleButtonClicked() = navigate(LoginFragmentDirections.toGoogleLogin())

    fun onFacebookButtonClicked() = navigate(LoginFragmentDirections.toFacebookLogin())

}