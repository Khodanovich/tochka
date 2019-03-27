package com.github.khodanovich.tochka.features.search.users.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import kotlinx.android.synthetic.main.item_search_user.view.*


class UserViewHolder constructor(item: View) : RecyclerView.ViewHolder(item) {

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_search_user, parent, false)
            return UserViewHolder(view)
        }
    }

    fun bind(userEntity: UserEntity?){
        userEntity?.let {
            itemView.name.text = userEntity.login
            Glide.with(itemView)
                .load(it.avatarUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_user)
                .into(itemView.findViewById(R.id.avatar))
        }
    }
}