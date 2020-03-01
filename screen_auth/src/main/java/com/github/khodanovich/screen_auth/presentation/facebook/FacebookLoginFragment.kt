package com.github.khodanovich.screen_auth.presentation.facebook

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.github.khodanovich.screen_auth.R
import com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


internal class FacebookLoginFragment : GlobalFragment(), FacebookCallback<LoginResult> {

    companion object {
        private const val PUBLIC_PROFILE_PERMISSION = "public_profile"
        private val READ_PERMISSIONS = listOf(PUBLIC_PROFILE_PERMISSION)
    }

    override val layoutRes = R.layout.fmt_facebook_login

    override val viewModel: FacebookLoginViewModel by viewModel()

    private val loginManager: LoginManager by inject()

    private val callbackManager = CallbackManager.Factory.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSignInActivity()
    }

    private fun startSignInActivity(){
        loginManager.registerCallback(callbackManager, this)
        loginManager.logInWithReadPermissions(this, READ_PERMISSIONS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccess(result: LoginResult?) = viewModel.onAuthSuccess()

    override fun onCancel() = viewModel.onAuthFail()

    override fun onError(error: FacebookException?) = viewModel.onAuthFail()

    override fun onDetach() {
        super.onDetach()
        LoginManager.getInstance().unregisterCallback(callbackManager)
    }

}