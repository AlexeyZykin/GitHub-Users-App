package com.alexisdev.github_users_app.cache.di

import android.content.Context
import androidx.room.Room
import com.alexisdev.github_users_app.cache.UsersDataBase
import org.koin.dsl.module

val usersCacheModule = module {
    single { provideDataBase(get()) }
    single { provideUsersDao(get()) }
}

private fun provideDataBase(context: Context): UsersDataBase {
    return Room.databaseBuilder(
        context,
        UsersDataBase::class.java,
        UsersDataBase.USER_TABLE_NAME
    ).build()
}

private fun provideUsersDao(usersDataBase: UsersDataBase) = usersDataBase.getUsersDao()