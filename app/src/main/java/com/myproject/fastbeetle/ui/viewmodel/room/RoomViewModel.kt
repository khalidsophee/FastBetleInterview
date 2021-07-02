package com.myproject.fastbeetle.ui.viewmodel.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RoomViewModel @Inject constructor() :ViewModel() {

    var liveDataUser: LiveData<List<UserEntity>>? = null

    fun insert(context: Context, emailId: String, lastName: String,
               imageUrl: String, firstName: String,time:String)
    {
       MyRepository.insert(context, emailId, lastName, imageUrl, firstName,time)
    }

    fun getAllUsers(context: Context) : LiveData<List<UserEntity>>? {
        liveDataUser = MyRepository.getAllUsers(context)
        return liveDataUser
    }

}