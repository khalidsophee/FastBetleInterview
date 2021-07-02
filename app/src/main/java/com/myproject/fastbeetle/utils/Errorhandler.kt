package com.myproject.fastbeetle.utils

import com.myproject.fastbeetle.data.remote.NetworkState
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException

inline fun <reified T> errorHandler(t: Throwable): NetworkState<T> {
    return when (t) {
        is IOException -> NetworkState.Failure(t.message)
        is HttpException -> {
            NetworkState.Failure(t.message)
        }
        is SocketException -> {
            NetworkState.Failure(t.message)
        }
        else -> NetworkState.Failure(CONNECTION_ERROR)
    }
}