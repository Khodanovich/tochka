package com.github.khodanovich.tochka.features.login.domain.repository

import com.github.khodanovich.tochka.features.login.data.model.AuthType
import io.reactivex.Completable


interface AuthRepository {
    fun setUpTypeAsVk()
    fun setUpTypeAsGoogle()
    fun setUpTypeAsFaceBook()
    fun getAuthType(): AuthType
    fun logout(): Completable
}