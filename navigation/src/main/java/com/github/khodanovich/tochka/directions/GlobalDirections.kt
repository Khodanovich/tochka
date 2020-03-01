package com.github.khodanovich.tochka.directions

import com.github.khodanovich.tochka.navigation.commands.NavigationCommand

interface GlobalDirections {

    fun toLogin(): NavigationCommand.GlobalEvent

    fun toSearchUser(): NavigationCommand.GlobalEvent

    fun toSearchUserWithPopUpToAuth(): NavigationCommand.GlobalEvent

    fun toAuthWithPopUpToSearchUsers(): NavigationCommand.GlobalEvent
}