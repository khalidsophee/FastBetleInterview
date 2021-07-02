package com.myproject.fastbeetle.di

import com.myproject.fastbeetle.data.remote.AuthenticationInterceptor
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor

@Module
abstract class AuthenticationModule() {
    @Binds
    abstract fun bindAuthInterceptor(authInterceptor: AuthenticationInterceptor): Interceptor
}