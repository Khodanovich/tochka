package com.github.khodanovich.screen_splash.presentation

import android.os.Bundle
import android.view.View
import com.github.khodanovich.screen_splash.R
import com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment
import org.koin.android.viewmodel.ext.android.viewModel


internal class SplashFragment : GlobalFragment() {

    override val layoutRes = R.layout.fmt_splash

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lockDrawerLayout()
        viewModel.onViewCreated()
    }
}