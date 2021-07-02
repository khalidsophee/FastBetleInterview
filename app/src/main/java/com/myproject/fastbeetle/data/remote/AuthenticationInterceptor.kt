package com.myproject.fastbeetle.data.remote

import com.myproject.fastbeetle.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        sessionManager.getSession()?.let { session ->
            builder.header("apitoken", session.token)
        }
        return chain.proceed(builder.build())
    }

}