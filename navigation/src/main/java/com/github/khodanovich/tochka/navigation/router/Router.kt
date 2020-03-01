package com.github.khodanovich.tochka.navigation.router

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand

interface Router {

    val naigationEvents: MutableLiveData<NavigationCommand>

    fun navigate(directions: NavDirections)

    fun navigateToGlobal(globalEvent: NavigationCommand)

    fun back()

    fun backTo(destinationId: Int, inclusive: Boolean = false)
}