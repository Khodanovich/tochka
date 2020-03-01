package com.github.khodanovich.screen_profile.presentation

import androidx.lifecycle.MutableLiveData
import com.github.khodanovich.ft_auth.domain.AuthInteractor
import com.github.khodanovich.screen_profile.domain.ProfileInteractor
import com.github.khodanovich.screen_profile.domain.model.ProfileInfoEntity
import com.github.khodanovich.tochka.core_util.extentions.async
import com.github.khodanovich.tochka.core_util.life_data.SingleLiveEvent
import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.router.Router
import com.github_khodanovich.tochka.core_ui.error_handler.ErrorHandler
import com.github_khodanovich.tochka.core_ui.event.LoginEvent
import com.github_khodanovich.tochka.core_ui.event.LoginEventBus
import com.github_khodanovich.tochka.core_ui.presentation.GlobalViewModel


internal class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val authInteractor: AuthInteractor,
    private val loginEventBus: LoginEventBus,
    private val errorHandler: ErrorHandler,
    private val globalDirections: GlobalDirections,
    router: Router
) : GlobalViewModel(), Router by router {

    val profile = MutableLiveData<ProfileInfoEntity>()
    val profileVisibility = SingleLiveEvent<Boolean>()

    fun onViewCreated() {
        loginEventBus.getData()
            .subscribe { onDispatchLoginEvent(it) }
            .onClearedUnsubscribe()
    }

    private fun onDispatchLoginEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            LoginEvent.LOGIN -> onLogin()
            LoginEvent.LOGOUT -> hideProfileViews()
        }
    }

    private fun onLogin() {
        profileInteractor.getProfileInfo(authInteractor.getAuthType().authType)
            .async()
            .subscribe(::onDispatchProfileInfo) { errorHandler.proceed(errorMessageLiveData, it) }
            .onClearedUnsubscribe()
    }

    private fun onDispatchProfileInfo(profileInfoEntity: ProfileInfoEntity){
        profile.value = profileInfoEntity
        showProfile()
    }

    private fun showProfile() {
        profileVisibility.value = true
    }

    private fun hideProfileViews() {
        profileVisibility.value = false
    }

    fun onLogoutClicked() {
        authInteractor.logout()
            .async()
            .subscribe(::onLogout) { errorHandler.proceed(errorMessageLiveData, it) }
            .onClearedUnsubscribe()
    }

    private fun onLogout() {
        loginEventBus.setLoginEvent(LoginEvent.LOGOUT)
        navigateToGlobal(globalDirections.toAuthWithPopUpToSearchUsers())
    }
}