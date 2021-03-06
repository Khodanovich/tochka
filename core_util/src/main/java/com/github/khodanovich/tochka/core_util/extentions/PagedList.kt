package com.github.khodanovich.tochka.core_util.extentions

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.paging.PagedList


@SuppressLint("RestrictedApi")
fun <K, V> (PagedList.Builder<K, V>).async() =
        this
            .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor())
            .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor())