package com.github.khodanovich.tochka.features.profile.domain.repository


import com.github.khodanovich.tochka.features.profile.data.model.GoogleProfileInfoResponse
import io.reactivex.Single


interface GoogleProfileRepository {
    fun getProfileInfo(): Single<GoogleProfileInfoResponse>
}