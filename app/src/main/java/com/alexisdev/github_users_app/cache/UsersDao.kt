package com.alexisdev.github_users_app.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM ${UsersDataBase.USER_TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, UserDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserDBO>)

    @Query("DELETE FROM ${UsersDataBase.USER_TABLE_NAME}")
    fun clearUsers()
}