package com.github.khodanovich.screen_profile.domain

import com.github.khodanovich.core_models.auth.AuthType
import com.github.khodanovich.screen_profile.domain.model.ProfileInfoEntity
import io.reactivex.Single

internal interface ProfileInteractor {
    fun getProfileInfo(authType: AuthType): Single<ProfileInfoEntity>
}