package com.github.khodanovich.tochka.di.modules

import com.github.khodanovich.tochka.features.profile.data.FacebookProfileRepositoryImpl
import com.github.khodanovich.tochka.features.profile.data.GoogleProfileRepositoryImpl
import com.github.khodanovich.tochka.features.profile.data.VkProfileRepositoryImpl
import com.github.khodanovich.tochka.features.profile.domain.ProfileInteractorImpl
import com.github.khodanovich.tochka.features.profile.domain.repository.FacebookProfileRepository
import com.github.khodanovich.tochka.features.profile.domain.repository.GoogleProfileRepository
import com.github.khodanovich.tochka.features.profile.domain.repository.VkProfileRepository
import com.github.khodanovich.tochka.features.profile.presentation.ProfileInteractor
import com.github.khodanovich.tochka.features.profile.presentation.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileModule = module {
    viewModel { ProfileViewModel(get(), get(), get(), get()) }

    single<ProfileInteractor> { ProfileInteractorImpl(get(), get(), get()) }

    single<VkProfileRepository> { VkProfileRepositoryImpl() }
    single<GoogleProfileRepository> { GoogleProfileRepositoryImpl(get()) }
    single<FacebookProfileRepository> { FacebookProfileRepositoryImpl() }
}