package com.github.khodanovich.screen_search_users.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.khodanovich.screen_search_users.R
import com.github.khodanovich.screen_search_users.presentation.pagination.LoadState
import com.github.khodanovich.screen_search_users.presentation.pagination.Status

internal class LoadStateViewHolder constructor(item: View) : RecyclerView.ViewHolder(item) {

    val onChangeLoadStateListener: (LoadState) -> Unit = {
        when(it.status){
            Status.SUCCESS -> itemView.visibility = View.INVISIBLE
            Status.RUNNING -> itemView.visibility = View.VISIBLE
            Status.FAILED -> {
                itemView.visibility = View.INVISIBLE
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): LoadStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_progress_bar, parent, false)
            return LoadStateViewHolder(view)
        }
    }
}