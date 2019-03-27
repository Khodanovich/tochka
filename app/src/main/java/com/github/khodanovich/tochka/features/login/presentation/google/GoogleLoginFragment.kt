package com.github.khodanovich.tochka.features.login.presentation.google

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.github.khodanovich.tochka.features.global.view.GlobalFragment
import com.github.khodanovich.tochka.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class GoogleLoginFragment : GlobalFragment() {

    companion object {
        private const val RC_SIGN_IN = 1001
    }

    override val layoutRes = R.layout.fmt_google_login

    override val viewModel: GoogleLoginViewModel by viewModel()

    private val googleSignInClient: GoogleSignInClient by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSignInActivity()
    }

    private fun startSignInActivity() = startActivityForResult(makeIntent(), RC_SIGN_IN)

    private fun makeIntent() = googleSignInClient.signInIntent

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                if (requestCode == RC_SIGN_IN) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    handleSignInResult(task)
                }
            }
            Activity.RESULT_CANCELED -> viewModel.onAuthFail()
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) =
        try {
            val result = completedTask.getResult(ApiException::class.java)
            viewModel.onAuthSuccess()
        } catch (e: ApiException) {
            viewModel.onAuthFail()
        }
}