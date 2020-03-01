package com.github.khodanovich.tochka.di

import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.directions.GlobalDirectionsImpl
import org.koin.dsl.module

val appModule = module {
    single<GlobalDirections> { GlobalDirectionsImpl() }
}