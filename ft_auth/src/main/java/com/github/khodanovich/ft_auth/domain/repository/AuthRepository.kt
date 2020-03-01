package com.github.khodanovich.ft_auth.domain.repository

import com.github.khodanovich.core_models.auth.AuthType
import io.reactivex.Completable


internal interface AuthRepository {
    fun setUpTypeAsVk()
    fun setUpTypeAsGoogle()
    fun setUpTypeAsFaceBook()
    fun getAuthType(): AuthType
    fun logout(): Completable
}