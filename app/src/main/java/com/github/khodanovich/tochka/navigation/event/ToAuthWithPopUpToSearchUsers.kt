package com.github.khodanovich.tochka.navigation.event

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand

class ToAuthWithPopUpToSearchUsers : NavigationCommand.GlobalEvent {

    override fun navigate(fragment: Fragment) {
        return fragment.findNavController().navigate(
            R.id.toAuthWithPopUpToSearchUsers,
            null,
            navOptions { popUpTo = R.id.toSearchUser}
        )
    }
}