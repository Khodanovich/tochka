package com.github.khodanovich.tochka.features.profile.domain.repository

import com.github.khodanovich.tochka.features.profile.data.model.VkProfileInfoResponse
import io.reactivex.Single


interface VkProfileRepository {
    fun getProfileInfo(): Single<VkProfileInfoResponse>
}