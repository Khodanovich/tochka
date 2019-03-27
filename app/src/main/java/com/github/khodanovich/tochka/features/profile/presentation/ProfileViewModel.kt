package com.github.khodanovich.tochka.features.profile.presentation

import androidx.lifecycle.MutableLiveData
import com.github.khodanovich.tochka.features.global.error.ErrorHandler
import com.github.khodanovich.tochka.features.global.event.LoginEvent
import com.github.khodanovich.tochka.features.global.event.LoginEventBus
import com.github.khodanovich.tochka.features.global.livedata.SingleLiveEvent
import com.github.khodanovich.tochka.features.global.view.GlobalViewModel
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor
import com.github.khodanovich.tochka.features.profile.domain.model.ProfileInfoEntity
import com.github.khodanovich.tochka.features.search.users.presentation.SearchUsersFragmentDirections
import com.github.khodanovich.tochka.util.extensions.async


class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val authInteractor: AuthInteractor,
    private val loginEventBus: LoginEventBus,
    private val errorHandler: ErrorHandler
) : GlobalViewModel() {

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
        navigate(SearchUsersFragmentDirections.toLogin())
    }
}