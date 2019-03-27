package com.github.khodanovich.tochka.features.global.system.preferences

import android.content.Context
import com.github.khodanovich.tochka.features.login.data.model.AuthType


class SharedPreferences constructor(context: Context) {
    private val applicationContext: Context = context.applicationContext

    var isAuthenticated by applicationContext.bindPreference(false)
    var authType by applicationContext.bindEnumPreference(AuthType.NOT_AUTHORIZED)
}