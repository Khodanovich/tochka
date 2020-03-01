package com.github.khodanovich.ft_auth.domain

import com.github.khodanovich.ft_auth.domain.model.AuthTypeEntity
import io.reactivex.Completable


interface AuthInteractor {
    fun setUpAuthTypeAsVk()
    fun setUpAuthTypeAsGoogle()
    fun setUpAuthTypeAsFaceBook()
    fun getAuthType(): AuthTypeEntity
    fun logout(): Completable
}