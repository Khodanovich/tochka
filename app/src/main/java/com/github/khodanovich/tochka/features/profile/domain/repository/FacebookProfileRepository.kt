package com.github.khodanovich.tochka.features.profile.domain.repository

import com.github.khodanovich.tochka.features.profile.data.model.FacebookProfileInfoResponse
import io.reactivex.Single


interface FacebookProfileRepository {
    fun getProfileInfo(): Single<FacebookProfileInfoResponse>
}