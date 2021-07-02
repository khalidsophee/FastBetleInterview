package com.myproject.fastbeetle.di

import androidx.lifecycle.ViewModel
import com.myproject.fastbeetle.ui.viewmodel.LoginViewModel
import com.myproject.fastbeetle.ui.viewmodel.room.RoomViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomViewModel::class)
    abstract fun bindRoomViewModel(viewModel: RoomViewModel): ViewModel
}