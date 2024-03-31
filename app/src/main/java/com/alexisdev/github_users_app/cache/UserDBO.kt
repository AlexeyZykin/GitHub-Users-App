package com.alexisdev.github_users_app.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = UsersDataBase.USER_TABLE_NAME)
data class UserDBO(
    @ColumnInfo("login") val login: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("avatarUrl") val avatarUrl: String
)