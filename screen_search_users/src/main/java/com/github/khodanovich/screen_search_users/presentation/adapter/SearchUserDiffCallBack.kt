package com.github.khodanovich.screen_search_users.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.khodanovich.screen_search_users.domain.model.UserEntity

internal object SearchUserDiffCallBack : DiffUtil.ItemCallback<UserEntity>(){

    override fun areItemsTheSame(old: UserEntity, new: UserEntity) = old.id == new.id

    override fun areContentsTheSame(old: UserEntity, new: UserEntity) = old == new

}