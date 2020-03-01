package com.github.khodanovich.tochka.di

import com.github.khodanovich.tochka.navigation.navigator.Navigator
import com.github.khodanovich.tochka.navigation.navigator.NavigatorImpl
import com.github.khodanovich.tochka.navigation.router.Router
import com.github.khodanovich.tochka.navigation.router.RouterIml
import org.koin.dsl.module

val navigationModule = module {

    single<Router> { RouterIml() }
    single<Navigator> {
        NavigatorImpl(
            get()
        )
    }
}