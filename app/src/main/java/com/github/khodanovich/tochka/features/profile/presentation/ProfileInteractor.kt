package com.github.khodanovich.tochka.features.profile.presentation

import com.github.khodanovich.tochka.features.login.data.model.AuthType
import com.github.khodanovich.tochka.features.profile.domain.model.ProfileInfoEntity
import io.reactivex.Single


interface ProfileInteractor {
    fun getProfileInfo(authType: AuthType): Single<ProfileInfoEntity>
}