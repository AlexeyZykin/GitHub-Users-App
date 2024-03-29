package com.alexisdev.github_users_app.features.user_list.di

import com.alexisdev.github_users_app.features.user_list.data.UserListRepositoryImpl
import com.alexisdev.github_users_app.features.user_list.domain.FetchUsersUseCase
import com.alexisdev.github_users_app.features.user_list.domain.UserListRepository
import com.alexisdev.github_users_app.features.user_list.presentation.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userListFeatureModule = module {
    single<UserListRepository> { UserListRepositoryImpl(get()) }
    factory { FetchUsersUseCase(get()) }
    viewModel { UserListViewModel(get()) }
}