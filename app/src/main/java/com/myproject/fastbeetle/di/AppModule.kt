package com.myproject.antinolabs.di


import android.app.Application
import com.myproject.fastbeetle.di.AuthenticationModule
import com.myproject.fastbeetle.di.NetworkModule
import com.myproject.fastbeetle.di.ViewModelsModule
import com.myproject.fastbeetle.utils.SessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        AuthenticationModule::class,
        ViewModelsModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(app: Application): SessionManager {
        return SessionManager(app)
    }
}

