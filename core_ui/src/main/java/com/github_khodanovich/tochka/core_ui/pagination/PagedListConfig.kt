package com.github_khodanovich.tochka.core_ui.pagination

import androidx.paging.PagedList

const val SEARCH_PAGE_SIZE = 30

val searchConfig = PagedList.Config.Builder()
    .setEnablePlaceholders(false)
    .setPageSize(SEARCH_PAGE_SIZE)
    .build()