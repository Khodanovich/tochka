package com.github.khodanovich.tochka.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.github.khodanovich.tochka.R
import com.github_khodanovich.tochka.core_ui.presentation.LockDrawerLayout
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity : AppCompatActivity(), LockDrawerLayout {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun lockDrawerLayout() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawerLayout() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}
