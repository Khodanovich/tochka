package com.github.khodanovich.tochka.features.splash.view

import android.os.Bundle
import android.view.View
import com.github.khodanovich.tochka.features.global.view.GlobalFragment
import com.github.khodanovich.tochka.R
import org.koin.android.viewmodel.ext.android.viewModel


class SplashFragment : GlobalFragment() {

    override val layoutRes = R.layout.fmt_splash

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lockDrawerLayout()
        viewModel.onViewCreated()
    }
}