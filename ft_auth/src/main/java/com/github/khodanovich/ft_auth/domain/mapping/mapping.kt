package com.github.khodanovich.ft_auth.domain.mapping

import com.github.khodanovich.core_models.auth.AuthType
import com.github.khodanovich.ft_auth.domain.model.AuthTypeEntity

internal fun AuthType.toEntity() = AuthTypeEntity(this)