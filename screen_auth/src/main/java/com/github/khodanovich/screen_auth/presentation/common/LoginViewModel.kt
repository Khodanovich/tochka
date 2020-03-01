package com.github.khodanovich.screen_auth.presentation.common

import com.github.khodanovich.tochka.navigation.router.Router
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel


internal class LoginViewModel(
    router: Router
) : GlobalViewModel(), Router by router {

    fun onVkButtonClicked() = navigate(LoginFragmentDirections.toVkLogin())

    fun onGoogleButtonClicked() = navigate(LoginFragmentDirections.toGoogleLogin())

    fun onFacebookButtonClicked() = navigate(LoginFragmentDirections.toFacebookLogin())

}