package com.github.khodanovich.ft_auth.domain

import com.github.khodanovich.ft_auth.domain.mapping.toEntity
import com.github.khodanovich.ft_auth.domain.repository.AuthRepository


internal class AuthInteractorImpl(private val authRepository: AuthRepository) : AuthInteractor {

    override fun setUpAuthTypeAsVk() = authRepository.setUpTypeAsVk()

    override fun setUpAuthTypeAsGoogle() = authRepository.setUpTypeAsGoogle()

    override fun setUpAuthTypeAsFaceBook() = authRepository.setUpTypeAsFaceBook()

    override fun getAuthType() = authRepository.getAuthType().toEntity()

    override fun logout() = authRepository.logout()

}