package com.github.khodanovich.tochka.features.profile.data

import com.github.khodanovich.tochka.features.profile.data.model.VkProfileInfoResponse
import com.github.khodanovich.tochka.features.profile.domain.repository.VkProfileRepository
import com.vk.sdk.api.VKApi
import com.vk.sdk.api.VKError
import com.vk.sdk.api.VKRequest
import com.vk.sdk.api.VKResponse
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import io.reactivex.Single


class VkProfileRepositoryImpl : VkProfileRepository {

    override fun getProfileInfo(): Single<VkProfileInfoResponse> {
        return Single.create { emitter ->
            makeVKRequestListener(
                { emitter.onSuccess(it) },
                { emitter.onError(it) })
        }
    }

    private fun makeVKRequestListener(onComplete: (VkProfileInfoResponse) -> Unit, onError: (Exception) -> Unit) =
        VKApi.users().get().executeWithListener(object : VKRequest.VKRequestListener() {

            override fun onComplete(response: VKResponse) {
                val users = response.parsedModel as VKList<VKApiUser>
                val user = users.component1()

                onComplete(
                    VkProfileInfoResponse(
                        name = "${user.first_name} ${user.last_name}",
                        avatarUrl = user.photo_100
                    )
                )
            }

            override fun onError(error: VKError) {
                onError(Exception(error.errorMessage))
            }
        })
}