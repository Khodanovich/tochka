package com.github.khodanovich.tochka.di.modules

import android.content.Context
import com.facebook.login.LoginManager
import com.github.khodanovich.tochka.features.global.error.ErrorHandler
import com.github.khodanovich.tochka.features.global.error.ErrorHandlerImpl
import com.github.khodanovich.tochka.features.global.event.LoginEventBus
import com.github.khodanovich.tochka.features.login.data.AuthRepositoryImpl
import com.github.khodanovich.tochka.features.login.domain.interactor.AuthInteractorImpl
import com.github.khodanovich.tochka.features.login.domain.repository.AuthRepository
import com.github.khodanovich.tochka.features.login.presentation.common.LoginViewModel
import com.github.khodanovich.tochka.features.login.presentation.facebook.FacebookLoginViewModel
import com.github.khodanovich.tochka.features.login.presentation.google.GoogleLoginViewModel
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor
import com.github.khodanovich.tochka.features.login.presentation.vk.VkLoginViewModel
import com.github.khodanovich.tochka.features.splash.view.SplashViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val authModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { VkLoginViewModel(get()) }
    viewModel { GoogleLoginViewModel(get()) }
    viewModel { FacebookLoginViewModel(get(), get()) }

    single { createGoogleClient(get()) }
    single { createFacebookLoginManager() }

    single<AuthInteractor> { AuthInteractorImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single { LoginEventBus() }

    single<ErrorHandler> { ErrorHandlerImpl() }
}

private fun createFacebookLoginManager() = LoginManager.getInstance()

private fun createGoogleClient(context: Context): GoogleSignInClient {
    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(context, options)
}

