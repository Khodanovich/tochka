package com.github.khodanovich.tochka

import android.app.Application
import com.github.khodanovich.core_api.di.apiModule
import com.github.khodanovich.screen_auth.di.authModule
import com.github.khodanovich.screen_profile.di.profileModule
import com.github.khodanovich.screen_search_users.di.searchUserModule
import com.github.khodanovich.tochka.core_util.di.coreUtilModule
import com.github.khodanovich.screen_splash.di.splashModule
import com.github.khodanovich.ft_auth.di.featureAuthModule
import com.github.khodanovich.tochka.di.appModule
import com.github.khodanovich.tochka.di.navigationModule
import com.vk.sdk.VKSdk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App :Application() {

    override fun onCreate() {
        super.onCreate()

        initDI()
        initVkSdk()
    }

    private fun initDI() {
        startKoin{
            androidContext(this@App)
            modules(
                authModule,
                coreUtilModule,
                apiModule,
                appModule,
                navigationModule,
                featureAuthModule,
                splashModule,
                profileModule,
                searchUserModule
            )
        }
    }

    private fun initVkSdk(){
        VKSdk.initialize(applicationContext)
    }
}