package com.github.khodanovich.tochka.navigation.navigator

import androidx.fragment.app.Fragment

interface Navigator {

    fun attach(fragment: Fragment)

    fun detach(fragment: Fragment)

}