package com.github.khodanovich.tochka.di.modules

import com.github.khodanovich.tochka.features.global.system.preferences.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {
    single { SharedPreferences(androidContext()) }
}