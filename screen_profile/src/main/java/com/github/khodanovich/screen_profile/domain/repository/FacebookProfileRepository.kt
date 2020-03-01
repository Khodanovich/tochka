package com.github.khodanovich.screen_profile.domain.repository

import com.github.khodanovich.screen_profile.data.model.FacebookProfileInfoResponse
import io.reactivex.Single


internal interface FacebookProfileRepository {
    fun getProfileInfo(): Single<FacebookProfileInfoResponse>
}