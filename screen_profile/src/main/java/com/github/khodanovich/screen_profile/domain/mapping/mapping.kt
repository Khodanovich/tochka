package com.github.khodanovich.screen_profile.domain.mapping

import com.github.khodanovich.screen_profile.data.model.FacebookProfileInfoResponse
import com.github.khodanovich.screen_profile.data.model.GoogleProfileInfoResponse
import com.github.khodanovich.screen_profile.data.model.VkProfileInfoResponse
import com.github.khodanovich.screen_profile.domain.model.ProfileInfoEntity
import io.reactivex.Single


internal fun Single<VkProfileInfoResponse>.toVkProfileEntity() =
    this.map {
        ProfileInfoEntity(
            name = it.name,
            avatarUrl = it.avatarUrl
        )
    }

internal fun Single<GoogleProfileInfoResponse>.toGoogleProfileEntity() =
        this.map {
            ProfileInfoEntity(
                name = it.name,
                avatarUrl = it.avatarUrl
            )
        }

internal fun Single<FacebookProfileInfoResponse>.toFacebookProfileEntity() =
        this.map {
            ProfileInfoEntity(
                name = it.name,
                avatarUrl = it.avatarUrl
            )
        }