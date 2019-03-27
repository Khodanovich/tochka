package com.github.khodanovich.tochka.features.search.users.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.khodanovich.tochka.R
import com.github.khodanovich.tochka.features.search.users.domain.model.UserEntity
import com.github.khodanovich.tochka.features.search.users.presentation.pagination.LoadState


class SearchUsersAdapter : PagedListAdapter<UserEntity, RecyclerView.ViewHolder>(SearchUserDiffCallBack) {

    private lateinit var onChangeLoadStateListener: (LoadState) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_search_user -> UserViewHolder.create(parent)
            R.layout.item_progress_bar -> LoadStateViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_search_user -> (holder as UserViewHolder).bind(getItem(position))
            R.layout.item_progress_bar -> (holder as LoadStateViewHolder).apply {
                this@SearchUsersAdapter.onChangeLoadStateListener = onChangeLoadStateListener
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) R.layout.item_progress_bar
        else R.layout.item_search_user

    }

    override fun getItemCount() = super.getItemCount() + 1

    fun setLoadState(loadState: LoadState?) {
        loadState?.let {
            onChangeLoadStateListener.invoke(loadState)
        }
    }
}