package com.github.khodanovich.tochka.features.login.domain.mapping

import com.github.khodanovich.tochka.features.login.data.model.AuthType
import com.github.khodanovich.tochka.features.login.domain.model.AuthTypeEntity


fun AuthType.toEntity() = AuthTypeEntity(this)