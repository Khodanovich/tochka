package com.github.khodanovich.tochka.features.login.domain.interactor

import com.github.khodanovich.tochka.features.login.domain.mapping.toEntity
import com.github.khodanovich.tochka.features.login.domain.repository.AuthRepository
import com.github.khodanovich.tochka.features.login.presentation.interactor.AuthInteractor


class AuthInteractorImpl(private val authRepository: AuthRepository) : AuthInteractor {

    override fun setUpAuthTypeAsVk() = authRepository.setUpTypeAsVk()

    override fun setUpAuthTypeAsGoogle() = authRepository.setUpTypeAsGoogle()

    override fun setUpAuthTypeAsFaceBook() = authRepository.setUpTypeAsFaceBook()

    override fun getAuthType() = authRepository.getAuthType().toEntity()

    override fun logout() = authRepository.logout()

}