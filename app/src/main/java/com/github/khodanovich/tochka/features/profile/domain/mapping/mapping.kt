package com.github.khodanovich.tochka.features.profile.domain.mapping

import com.github.khodanovich.tochka.features.profile.data.model.FacebookProfileInfoResponse
import com.github.khodanovich.tochka.features.profile.data.model.GoogleProfileInfoResponse
import com.github.khodanovich.tochka.features.profile.data.model.VkProfileInfoResponse
import com.github.khodanovich.tochka.features.profile.domain.model.ProfileInfoEntity
import io.reactivex.Single


fun Single<VkProfileInfoResponse>.toVkProfileEntity() =
    this.map {
        ProfileInfoEntity(
            name = it.name,
            avatarUrl = it.avatarUrl
        )
    }

fun Single<GoogleProfileInfoResponse>.toGoogleProfileEntity() =
        this.map {
            ProfileInfoEntity(
                name = it.name,
                avatarUrl = it.avatarUrl
            )
        }

fun Single<FacebookProfileInfoResponse>.toFacebookProfileEntity() =
        this.map {
            ProfileInfoEntity(
                name = it.name,
                avatarUrl = it.avatarUrl
            )
        }