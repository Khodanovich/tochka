package com.github.khodanovich.screen_splash.di

import com.github.khodanovich.screen_splash.presentation.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel { SplashViewModel(get(), get(), get()) }
}