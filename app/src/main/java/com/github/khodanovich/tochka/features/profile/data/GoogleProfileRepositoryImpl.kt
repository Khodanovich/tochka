package com.github.khodanovich.tochka.features.profile.data

import android.content.Context
import com.github.khodanovich.tochka.features.global.exceptions.AccountNotFoundException
import com.github.khodanovich.tochka.features.profile.data.model.GoogleProfileInfoResponse
import com.github.khodanovich.tochka.features.profile.domain.repository.GoogleProfileRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single


class GoogleProfileRepositoryImpl(private val context: Context) : GoogleProfileRepository {

    companion object {
        private const val DEFAULT_AVATAR_LINK = "https://i.stack.imgur.com/dr5qp.jpg"
    }

    override fun getProfileInfo(): Single<GoogleProfileInfoResponse> {
        return Single.create { emitter ->
            val account = GoogleSignIn.getLastSignedInAccount(context)
            if (account != null) {
                emitter.onSuccess(
                    GoogleProfileInfoResponse(
                        name = account.displayName.orEmpty(),
                        avatarUrl = getLinkFromAccountOrDefault(account)
                    )
                )
            } else {
                emitter.onError(AccountNotFoundException())
            }
        }
    }

    private fun getLinkFromAccountOrDefault(account: GoogleSignInAccount): String {
        val url = account.photoUrl
        return url?.toString() ?: DEFAULT_AVATAR_LINK
    }
}