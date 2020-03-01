package com.github.khodanovich.ft_auth.data

import com.facebook.login.LoginManager
import com.github.khodanovich.core_models.auth.AuthType
import com.github.khodanovich.ft_auth.domain.repository.AuthRepository
import com.github.khodanovich.tochka.core_util.preference.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.vk.sdk.VKSdk
import io.reactivex.Completable


internal class AuthRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val googleSignInClient: GoogleSignInClient
) : AuthRepository {

    override fun setUpTypeAsVk() {
        sharedPreferences.authType = AuthType.VK
        sharedPreferences.isAuthenticated = true
    }

    override fun setUpTypeAsGoogle() {
        sharedPreferences.authType = AuthType.GOOGLE
        sharedPreferences.isAuthenticated = true
    }

    override fun setUpTypeAsFaceBook() {
        sharedPreferences.authType = AuthType.FACEBOOK
        sharedPreferences.isAuthenticated = true
    }

    override fun getAuthType() = sharedPreferences.authType

    override fun logout() = Completable.create {
        when (sharedPreferences.authType) {
            AuthType.VK -> vkLogout()
            AuthType.GOOGLE -> googleLogout()
            AuthType.FACEBOOK -> facebookLogout()
        }
        sharedPreferences.isAuthenticated = false
        it.onComplete()
    }

    private fun vkLogout() = VKSdk.logout()

    private fun googleLogout() = googleSignInClient.revokeAccess()

    private fun facebookLogout() = LoginManager.getInstance().logOut()

}