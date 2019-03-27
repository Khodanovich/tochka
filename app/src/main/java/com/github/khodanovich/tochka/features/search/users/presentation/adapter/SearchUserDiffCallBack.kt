package com.github.khodanovich.tochka.features.search.users.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity


object SearchUserDiffCallBack : DiffUtil.ItemCallback<UserEntity>(){

    override fun areItemsTheSame(old: UserEntity, new: UserEntity) = old.id == new.id

    override fun areContentsTheSame(old: UserEntity, new: UserEntity) = old == new

}