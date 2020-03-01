package com.github.khodanovich.screen_search_users.presentation.pagination


internal enum class Status {
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

        fun error(msg: String?) =
            LoadState(
                Status.FAILED,
                msg
            )
    }
}

