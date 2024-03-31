package com.alexisdev.github_users_app.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDBO::class], version = 1, exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao

    companion object {
        const val USERS_DATABASE_NAME = "users_db"
        const val USER_TABLE_NAME = "user_table"
    }
}