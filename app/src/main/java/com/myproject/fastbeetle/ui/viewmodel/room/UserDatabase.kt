package com.myproject.fastbeetle.ui.viewmodel.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabaseClient(context: Context): UserDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        UserDatabase::class.java,
                        "FastBeetle"
                    )
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}