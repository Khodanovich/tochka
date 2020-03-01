package com.github.khodanovich.screen_auth.presentation.vk

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.github.khodanovich.screen_auth.R
import com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.VKServiceActivity
import com.vk.sdk.VKServiceActivity.VKServiceType
import com.vk.sdk.api.VKError
import org.koin.android.viewmodel.ext.android.viewModel


internal class VkLoginFragment : GlobalFragment(), VKCallback<VKAccessToken> {

    companion object {
        private const val KEY_TYPE = "arg1"
        private const val KEY_SCOPE_LIST = "arg2"
        private const val KEY_SDK_CUSTOM_INITIALIZE = "arg4"
    }

    override val layoutRes = R.layout.fmt_vk_login

    override val viewModel: VkLoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSignInActivity()
    }

    private fun startSignInActivity() =
        startActivityForResult(makeIntent(), VKServiceType.Authorization.outerCode)

    private fun makeIntent() = Intent(context, VKServiceActivity::class.java).apply {
        putExtra(KEY_TYPE, VKServiceType.Authorization.name)
        putExtra(KEY_SDK_CUSTOM_INITIALIZE, VKSdk.isCustomInitialize())
        putStringArrayListExtra(KEY_SCOPE_LIST, arrayListOf())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == VKServiceType.Authorization.outerCode)
            VKSdk.onActivityResult(requestCode, resultCode, data, this)
        else super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResult(res: VKAccessToken?) = viewModel.onAuthSuccess()

    override fun onError(error: VKError?) = viewModel.onAuthFail()
}