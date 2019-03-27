package com.github.khodanovich.tochka.features.profile.domain

import com.github.khodanovich.tochka.features.login.data.model.AuthType
import com.github.khodanovich.tochka.features.profile.domain.mapping.toFacebookProfileEntity
import com.github.khodanovich.tochka.features.profile.domain.mapping.toGoogleProfileEntity
import com.github.khodanovich.tochka.features.profile.domain.mapping.toVkProfileEntity
import com.github.khodanovich.tochka.features.profile.domain.model.ProfileInfoEntity
import com.github.khodanovich.tochka.features.profile.domain.repository.FacebookProfileRepository
import com.github.khodanovich.tochka.features.profile.domain.repository.GoogleProfileRepository
import com.github.khodanovich.tochka.features.profile.domain.repository.VkProfileRepository
import com.github.khodanovich.tochka.features.profile.presentation.ProfileInteractor
import io.reactivex.Single


class ProfileInteractorImpl(private val vkProfileRepository: VkProfileRepository,
                            private val googleProfileRepository: GoogleProfileRepository,
                            private val facebookProfileRepository: FacebookProfileRepository
) : ProfileInteractor {

    override fun getProfileInfo(authType: AuthType) : Single<ProfileInfoEntity> {
        return when(authType){
            AuthType.VK  -> vkProfileRepository.getProfileInfo().toVkProfileEntity()
            AuthType.GOOGLE -> googleProfileRepository.getProfileInfo().toGoogleProfileEntity()
            AuthType.FACEBOOK -> facebookProfileRepository.getProfileInfo().toFacebookProfileEntity()
            else -> { throw IllegalArgumentException() }
        }
    }
}