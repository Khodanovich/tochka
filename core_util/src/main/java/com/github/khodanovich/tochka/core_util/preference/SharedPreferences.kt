package com.github.khodanovich.tochka.core_util.preference

import android.content.Context
import com.github.khodanovich.core_models.auth.AuthType


class SharedPreferences constructor(val context: Context) {
    private val applicationContext: Context = context.applicationContext

    var isAuthenticated by applicationContext.bindPreference(false)
    var authType by bindEnumPreference(AuthType.NOT_AUTHORIZED)
}