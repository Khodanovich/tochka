package com.github.khodanovich.tochka.navigation.commands

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections


interface NavigationCommand {

    data class To(val directions: NavDirections): NavigationCommand

    object Back: NavigationCommand

    data class BackTo(val destinationId: Int, val inclusive: Boolean): NavigationCommand

    interface GlobalEvent : NavigationCommand{
        fun navigate(fragment: Fragment)
    }
}