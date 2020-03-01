package com.github.khodanovich.tochka.navigation.event

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand

internal class ToLoginEvent : NavigationCommand.GlobalEvent {

    override fun navigate(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.toAuth)
    }
}