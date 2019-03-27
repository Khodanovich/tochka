package com.github.khodanovich.tochka.features.global.event


class LoginEventBus : Bus<LoginEvent>() {

    fun setLoginEvent(event: LoginEvent){
        setData(event)
    }
}

enum class LoginEvent{
    LOGIN, LOGOUT
}