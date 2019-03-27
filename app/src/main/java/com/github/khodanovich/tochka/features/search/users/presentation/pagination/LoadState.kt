package com.github.khodanovich.tochka.features.search.users.presentation.pagination


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class LoadState private constructor(
    val status: Status,
    val message: String? = null
) {
    companion object {
        val LOADED = LoadState(Status.SUCCESS)
        val LOADING = LoadState(Status.RUNNING)
        fun error(msg: String?) = LoadState(Status.FAILED, msg)
    }
}

