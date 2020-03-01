package com.github.khodanovich.ft_auth.di

import com.github.khodanovich.ft_auth.data.AuthRepositoryImpl
import com.github.khodanovich.ft_auth.domain.AuthInteractor
import com.github.khodanovich.ft_auth.domain.AuthInteractorImpl
import com.github.khodanovich.ft_auth.domain.repository.AuthRepository
import org.koin.dsl.module

val featureAuthModule = module {
    single<AuthInteractor> { AuthInteractorImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}