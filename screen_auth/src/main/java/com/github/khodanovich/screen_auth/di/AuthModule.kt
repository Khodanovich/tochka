package com.github.khodanovich.screen_auth.di

import android.content.Context
import com.facebook.login.LoginManager
import com.github.khodanovich.screen_auth.presentation.common.LoginViewModel
import com.github.khodanovich.screen_auth.presentation.facebook.FacebookLoginViewModel
import com.github.khodanovich.screen_auth.presentation.google.GoogleLoginViewModel
import com.github.khodanovich.screen_auth.presentation.vk.VkLoginViewModel
import com.github_khodanovich.tochka.core_ui.error_handler.ErrorHandler
import com.github_khodanovich.tochka.core_ui.error_handler.ErrorHandlerImpl
import com.github_khodanovich.tochka.core_ui.event.LoginEventBus
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val authModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { VkLoginViewModel(get(), get(), get()) }
    viewModel { GoogleLoginViewModel(get(), get(), get()) }
    viewModel { FacebookLoginViewModel(get(), get(), get(), get()) }

    single { createGoogleClient(get()) }
    single { createFacebookLoginManager() }

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

