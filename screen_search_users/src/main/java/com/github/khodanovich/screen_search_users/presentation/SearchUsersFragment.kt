package com.github.khodanovich.screen_search_users.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.khodanovich.screen_search_users.R
import com.github.khodanovich.screen_search_users.presentation.adapter.SearchUsersAdapter
import com.github_khodanovich.tochka.core_ui.presentation.GlobalFragment
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fmt_search_users.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


internal class SearchUsersFragment : GlobalFragment() {

    companion object {
        private const val ENTER_SEARCH_TEXT_TIMEOUT = 600L
    }

    override val layoutRes = R.layout.fmt_search_users

    override val viewModel: SearchUsersViewModel by viewModel()

    private val adapter by lazy { SearchUsersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onViewCreated()
        unlockDrawerLayout()
        initSearch()
        initRecycler()
    }

    private fun initSearch(){
        RxTextView.textChanges(search)
            .skipInitialValue()
            .debounce(ENTER_SEARCH_TEXT_TIMEOUT, TimeUnit.MILLISECONDS)
            .subscribe { viewModel.onChangedSearchQuery(it.toString()) }
            .onDestroyUnsubscribe()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
        viewModel.usersLiveData.observe { adapter.submitList(it) }
        viewModel.loadState.observe{ adapter.setLoadState(it) }
    }
}