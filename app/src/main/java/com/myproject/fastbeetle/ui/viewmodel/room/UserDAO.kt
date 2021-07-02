package com.myproject.fastbeetle.ui.viewmodel.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loginTableModel: UserEntity)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<UserEntity>>?

}