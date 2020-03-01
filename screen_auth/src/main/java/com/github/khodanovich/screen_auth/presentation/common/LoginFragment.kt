package com.github.khodanovich.screen_auth.presentation.common

import android.os.Bundle
import android.view.View
import com.github.khodanovich.screen_auth.R
import com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment
import kotlinx.android.synthetic.main.fmt_login.*
import org.koin.android.viewmodel.ext.android.viewModel


internal class LoginFragment : GlobalFragment() {

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