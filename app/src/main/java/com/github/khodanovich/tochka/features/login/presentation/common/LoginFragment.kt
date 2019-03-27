package com.github.khodanovich.tochka.features.login.presentation.common

import android.os.Bundle
import android.view.View
import com.github.khodanovich.tochka.features.global.view.GlobalFragment
import com.github.khodanovich.tochka.R
import kotlinx.android.synthetic.main.fmt_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : GlobalFragment() {

    override val layoutRes = R.layout.fmt_login

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lockDrawerLayout()
        initListeners()
    }

    private fun initListeners() {
        vkButton.setOnClickListener { viewModel.onVkButtonClicked() }
        googleButton.setOnClickListener { viewModel.onGoogleButtonClicked() }
        facebookButton.setOnClickListener { viewModel.onFacebookButtonClicked() }
    }
}