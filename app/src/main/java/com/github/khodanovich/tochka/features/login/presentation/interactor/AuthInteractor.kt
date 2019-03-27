package com.github.khodanovich.tochka.features.login.presentation.interactor

import com.github.khodanovich.tochka.features.login.domain.model.AuthTypeEntity
import io.reactivex.Completable


interface AuthInteractor {
    fun setUpAuthTypeAsVk()
    fun setUpAuthTypeAsGoogle()
    fun setUpAuthTypeAsFaceBook()
    fun getAuthType(): AuthTypeEntity
    fun logout(): Completable
}