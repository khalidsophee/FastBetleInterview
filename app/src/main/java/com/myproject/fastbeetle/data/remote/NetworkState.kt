package com.myproject.fastbeetle.data.remote
sealed class NetworkState<out T> {
    class Loading<out T> : NetworkState<T>()
    data class Success<out T>(
        val items: T?,
        val status: String? = null
    ) : NetworkState<T>()

    data class Failure<out T>(val throwable: String?) : NetworkState<T>()
    data class Error<out T>(
        val message: String?,
        val errorCode: String = "",

    ) : NetworkState<T>()
}