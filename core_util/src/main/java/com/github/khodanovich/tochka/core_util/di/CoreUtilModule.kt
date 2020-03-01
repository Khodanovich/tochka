package com.github.khodanovich.tochka.core_util.di

import com.github.khodanovich.tochka.core_util.preference.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val coreUtilModule = module {
    single { SharedPreferences(androidContext()) }
}