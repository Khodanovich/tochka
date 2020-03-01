package com.github_khodanovich.tochka.core_ui.event


class LoginEventBus : Bus<LoginEvent>() {

    fun setLoginEvent(event: LoginEvent){
        setData(event)
    }
}

enum class LoginEvent{
    LOGIN, LOGOUT
}