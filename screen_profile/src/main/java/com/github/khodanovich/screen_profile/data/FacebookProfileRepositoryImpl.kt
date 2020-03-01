package com.github.khodanovich.screen_profile.data

import android.net.Uri
import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.Profile
import com.facebook.internal.Utility
import com.github.khodanovich.screen_profile.data.model.FacebookProfileInfoResponse
import com.github.khodanovich.screen_profile.domain.repository.FacebookProfileRepository
import io.reactivex.Single
import io.reactivex.SingleEmitter
import org.json.JSONObject


internal class FacebookProfileRepositoryImpl : FacebookProfileRepository {

    companion object {
        private const val DEFAULT_AVATAR_SIZE = 100

        private const val ID_JSON_FIELD_NAME = "id"
        private const val LINK_JSON_FIELD_NAME = "link"
        private const val FIRST_NAME_JSON_FIELD_NAME = "first_name"
        private const val MIDDLE_NAME_JSON_FIELD_NAME = "middle_name"
        private const val LAST_NAME_JSON_FIELD_NAME = "last_name"
        private const val NAME_JSON_FIELD_NAME = "name"

    }

    override fun getProfileInfo(): Single<FacebookProfileInfoResponse> {
        return Single.create { emitter ->
            try {
                val profile = Profile.getCurrentProfile()
                if (profile != null) onDispatchProfile(emitter, profile)
                else fetchProfile(emitter)
            } catch (e: Exception) {
                emitter.onError(Exception(e.message))
            }
        }
    }

    private fun fetchProfile(emitter: SingleEmitter<FacebookProfileInfoResponse>) {
        Utility.getGraphMeRequestWithCacheAsync(
            AccessToken.getCurrentAccessToken().token,
            object : Utility.GraphMeRequestWithCacheCallback {
                override fun onSuccess(userInfo: JSONObject) {
                    val profile = makeProfileFrom(userInfo)
                    Profile.setCurrentProfile(profile)
                    onDispatchProfile(emitter, profile)
                }

                override fun onFailure(error: FacebookException) {
                    emitter.onError(Exception(error.message))
                    return
                }
            })
    }

    private fun makeProfileFrom(userInfo: JSONObject): Profile {

        val id = userInfo.optString(ID_JSON_FIELD_NAME) ?: ""
        val firstName = userInfo.optString(FIRST_NAME_JSON_FIELD_NAME)
        val middleName = userInfo.optString(MIDDLE_NAME_JSON_FIELD_NAME)
        val lastName = userInfo.optString(LAST_NAME_JSON_FIELD_NAME)
        val name = userInfo.optString(NAME_JSON_FIELD_NAME)
        val link = userInfo.optString(LINK_JSON_FIELD_NAME)

        return Profile(
            id,
            firstName,
            middleName,
            lastName,
            name,
            if (link != null) Uri.parse(link) else null
        )
    }

    private fun onDispatchProfile(emitter: SingleEmitter<FacebookProfileInfoResponse>, profile: Profile) {
        emitter.onSuccess(
            FacebookProfileInfoResponse(
                name = "${profile.firstName} ${profile.lastName}",
                avatarUrl = profile.getProfilePictureUri(
                    DEFAULT_AVATAR_SIZE,
                    DEFAULT_AVATAR_SIZE
                ).toString()
            )
        )
    }
}