package com.github.khodanovich.tochka.navigation.router

import androidx.navigation.NavDirections
import com.github.khodanovich.tochka.core_util.life_data.SingleLiveEvent
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand

internal class RouterIml : Router {

    override val naigationEvents = SingleLiveEvent<NavigationCommand>()

    override fun navigate(directions: NavDirections) {
        naigationEvents.postValue(
            NavigationCommand.To(
                directions
            )
        )
    }

    override fun navigateToGlobal(globalEvent: NavigationCommand) {
        naigationEvents.postValue(globalEvent)
    }

    override fun back() {
        naigationEvents.postValue(NavigationCommand.Back)
    }

    override fun backTo(destinationId: Int, inclusive: Boolean) {
        naigationEvents.postValue(
            NavigationCommand.BackTo(
                destinationId,
                inclusive
            )
        )
    }
}