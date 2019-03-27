package com.github.khodanovich.tochka

import android.app.Application
import com.github.khodanovich.tochka.di.modules.*
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
                appModule,
                githubModule,
                profileModule
            )
        }
    }

    private fun initVkSdk(){
        VKSdk.initialize(applicationContext)
    }
}