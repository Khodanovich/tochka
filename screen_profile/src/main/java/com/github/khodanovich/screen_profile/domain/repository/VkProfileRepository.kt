package com.github.khodanovich.screen_profile.domain.repository

import com.github.khodanovich.screen_profile.data.model.VkProfileInfoResponse
import io.reactivex.Single


internal interface VkProfileRepository {
    fun getProfileInfo(): Single<VkProfileInfoResponse>
}