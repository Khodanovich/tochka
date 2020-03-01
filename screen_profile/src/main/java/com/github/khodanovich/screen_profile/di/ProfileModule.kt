package com.github.khodanovich.screen_profile.di

import com.github.khodanovich.screen_profile.data.FacebookProfileRepositoryImpl
import com.github.khodanovich.screen_profile.data.GoogleProfileRepositoryImpl
import com.github.khodanovich.screen_profile.data.VkProfileRepositoryImpl
import com.github.khodanovich.screen_profile.domain.ProfileInteractorImpl
import com.github.khodanovich.screen_profile.domain.repository.FacebookProfileRepository
import com.github.khodanovich.screen_profile.domain.repository.GoogleProfileRepository
import com.github.khodanovich.screen_profile.domain.repository.VkProfileRepository
import com.github.khodanovich.screen_profile.domain.ProfileInteractor
import com.github.khodanovich.screen_profile.presentation.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileModule = module {

    viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get()) }

    single<ProfileInteractor> { ProfileInteractorImpl(get(), get(), get()) }

    single<VkProfileRepository> { VkProfileRepositoryImpl() }
    single<GoogleProfileRepository> { GoogleProfileRepositoryImpl(get()) }
    single<FacebookProfileRepository> { FacebookProfileRepositoryImpl() }
}