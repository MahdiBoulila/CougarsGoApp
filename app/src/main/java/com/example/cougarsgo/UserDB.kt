package com.example.cougarsgo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1)
abstract class UserDB: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    companion object {
        private var INSTANT: UserDB? = null
        fun getDBObject(context: Context): UserDB? {
            if (INSTANT == null) {
                synchronized(UserDB::class.java) {
                    INSTANT = Room.databaseBuilder(context, UserDB::class.java, "userDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANT
        }
    }
}