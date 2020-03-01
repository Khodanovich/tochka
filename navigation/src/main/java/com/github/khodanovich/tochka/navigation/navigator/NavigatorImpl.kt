package com.github.khodanovich.tochka.navigation.navigator

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.khodanovich.tochka.navigation.commands.NavigationCommand
import com.github.khodanovich.tochka.navigation.router.Router
import java.lang.IllegalArgumentException

internal class NavigatorImpl(

    private val router: Router

) : Navigator {

    @Throws(IllegalArgumentException::class)
    private fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        this.observe(lifecycleOwner, Observer<T> { value -> observer.invoke(value) })
    }

    override fun attach(fragment: Fragment) {
        router.naigationEvents.observe(fragment) { command ->
            when (command) {
                is NavigationCommand.To -> {
                    fragment.findNavController().navigate(command.directions)
                }
                is NavigationCommand.Back -> {
                    fragment.findNavController().popBackStack()
                }
                is NavigationCommand.BackTo -> {
                    fragment.findNavController()
                        .popBackStack(command.destinationId, command.inclusive)
                }
                is NavigationCommand.GlobalEvent ->{
                    command.navigate(fragment)
                }
            }
        }
    }

    override fun detach(fragment: Fragment) {
        router.naigationEvents.removeObservers(fragment)
    }
}