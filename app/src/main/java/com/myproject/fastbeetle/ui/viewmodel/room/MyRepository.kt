package com.myproject.fastbeetle.ui.viewmodel.room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.sql.Time

class MyRepository {

    companion object {

        var database: UserDatabase? = null

        var tableModel: LiveData<List<UserEntity>>? = null

        fun initializeDB(context: Context): UserDatabase {
            return UserDatabase.getDatabaseClient(context)
        }

        fun insert(
            context: Context, emailId: String, lastName: String, imageUrl: String,
            firstName: String,time:String
        ) {

            database = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = UserEntity(emailId, lastName, imageUrl, firstName,time)
                database?.userDao()?.insert(loginDetails)
            }

        }

        fun getAllUsers(context: Context): LiveData<List<UserEntity>>? {
            database = initializeDB(context)

            tableModel = database?.userDao()?.getAllUsers()

            return tableModel
        }

    }
}