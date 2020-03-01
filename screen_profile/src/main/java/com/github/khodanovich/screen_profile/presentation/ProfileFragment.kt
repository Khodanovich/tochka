package com.github.khodanovich.screen_profile.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.github.khodanovich.screen_profile.R
import com.github.khodanovich.tochka.core_util.extentions.hide
import com.github.khodanovich.tochka.core_util.extentions.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fmt_profile.*
import org.koin.android.viewmodel.ext.android.viewModel


internal class ProfileFragment : com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment(), LifecycleOwner {

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
            .resize(
                SIZE_AVATAR,
                SIZE_AVATAR
            )
            .centerCrop()
            .into(avatar)
    }

    private fun initLogoutListener() =
        logout.setOnClickListener { viewModel.onLogoutClicked() }
}