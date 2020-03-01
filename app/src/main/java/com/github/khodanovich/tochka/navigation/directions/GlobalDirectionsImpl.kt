package com.github.khodanovich.tochka.navigation.directions

import com.github.khodanovich.tochka.directions.GlobalDirections
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand
import com.github.khodanovich.tochka.navigation.event.ToAuthWithPopUpToSearchUsers
import com.github.khodanovich.tochka.navigation.event.ToLoginEvent
import com.github.khodanovich.tochka.navigation.event.ToSearchUserEvent
import com.github.khodanovich.tochka.navigation.event.ToSearchUserWithPopUpToAuth

class GlobalDirectionsImpl : GlobalDirections {

    override fun toLogin(): NavigationCommand.GlobalEvent {
        return ToLoginEvent()
    }

    override fun toSearchUser(): NavigationCommand.GlobalEvent  {
        return ToSearchUserEvent()
    }

    override fun toSearchUserWithPopUpToAuth(): NavigationCommand.GlobalEvent {
        return ToSearchUserWithPopUpToAuth()
    }

    override fun toAuthWithPopUpToSearchUsers(): NavigationCommand.GlobalEvent {
        return ToAuthWithPopUpToSearchUsers()
    }
}