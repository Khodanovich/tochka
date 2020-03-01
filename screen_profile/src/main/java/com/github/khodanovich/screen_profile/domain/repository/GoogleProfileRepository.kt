package com.github.khodanovich.screen_profile.domain.repository


import com.github.khodanovich.screen_profile.data.model.GoogleProfileInfoResponse
import io.reactivex.Single


internal interface GoogleProfileRepository {
    fun getProfileInfo(): Single<GoogleProfileInfoResponse>
}