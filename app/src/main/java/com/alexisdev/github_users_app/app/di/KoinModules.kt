package com.alexisdev.github_users_app.app.di

import com.alexisdev.github_users_app.features.user_list.di.userListFeatureModule
import com.alexisdev.github_users_app.users_api.di.usersApiModule

val koinModules = listOf(
    usersApiModule,
    userListFeatureModule
)