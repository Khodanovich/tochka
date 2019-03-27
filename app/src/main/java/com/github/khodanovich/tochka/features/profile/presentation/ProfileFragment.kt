package com.github.khodanovich.tochka.features.profile.presentation

import android.os.Bundle
import android.view.View
import com.github.khodanovich.tochka.features.global.view.GlobalFragment
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.util.extensions.hide
import com.github.khodanovich.tochka.util.extensions.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fmt_profile.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : GlobalFragment() {

    companion object {
        private const val SIZE_AVATAR = 48
    }

    override val layoutRes = R.layout.fmt_profile

    override val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProfileViews()
        initLogoutListener()
        viewModel.onViewCreated()
    }

    private fun initProfileViews() {
        viewModel.profile.observe {
            name.text = it.name
            loadAvatar(it.avatarUrl)
        }
        viewModel.profileVisibility.observe { isVisible ->
            if (isVisible) {
                name.show()
                avatar.show()
            } else {
                name.hide()
                avatar.hide()
            }
        }
    }

    private fun loadAvatar(avatarUri: String) {
        Picasso.get()
            .load(avatarUri)
            .resize(SIZE_AVATAR, SIZE_AVATAR)
            .centerCrop()
            .into(avatar)
    }

    private fun initLogoutListener() =
        logout.setOnClickListener { viewModel.onLogoutClicked() }
}