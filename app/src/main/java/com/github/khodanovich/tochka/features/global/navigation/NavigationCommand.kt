package com.github.khodanovich.tochka.features.global.navigation

import androidx.navigation.NavDirections


sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()
    data class BackTo(val destinationId: Int, val inclusive: Boolean): NavigationCommand()
}