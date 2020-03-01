package com.github.khodanovich.screen_profile.domain

import com.github.khodanovich.core_models.auth.AuthType
import com.github.khodanovich.screen_profile.domain.mapping.toFacebookProfileEntity
import com.github.khodanovich.screen_profile.domain.mapping.toGoogleProfileEntity
import com.github.khodanovich.screen_profile.domain.mapping.toVkProfileEntity
import com.github.khodanovich.screen_profile.domain.model.ProfileInfoEntity
import com.github.khodanovich.screen_profile.domain.repository.FacebookProfileRepository
import com.github.khodanovich.screen_profile.domain.repository.GoogleProfileRepository
import com.github.khodanovich.screen_profile.domain.repository.VkProfileRepository
import io.reactivex.Single

internal class ProfileInteractorImpl(private val vkProfileRepository: VkProfileRepository,
                            private val googleProfileRepository: GoogleProfileRepository,
                            private val facebookProfileRepository: FacebookProfileRepository
) : ProfileInteractor {

    override fun getProfileInfo(authType: AuthType) : Single<ProfileInfoEntity> {
        return when(authType){
            AuthType.VK -> vkProfileRepository.getProfileInfo().toVkProfileEntity()
            AuthType.GOOGLE -> googleProfileRepository.getProfileInfo().toGoogleProfileEntity()
            AuthType.FACEBOOK -> facebookProfileRepository.getProfileInfo().toFacebookProfileEntity()
            else -> { throw IllegalArgumentException() }
        }
    }
}