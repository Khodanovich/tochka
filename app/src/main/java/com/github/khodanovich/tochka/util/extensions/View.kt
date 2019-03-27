package com.github.khodanovich.tochka.util.extensions

import android.view.View


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}