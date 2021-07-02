package com.myproject.fastbeetle.ui.viewmodel.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "emailId")
    var emailId: String = "",

    @ColumnInfo(name = "lastName")
    var lastName: String = "",

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",


    @ColumnInfo(name = "firstName")
    var firstName: String = "",

    @ColumnInfo(name = "time")
    var time: String = ""

)

