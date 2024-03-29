package com.alexisdev.github_users_app.features.user_details.di

import com.alexisdev.github_users_app.features.user_details.data.UserDetailsRepositoryImpl
import com.alexisdev.github_users_app.features.user_details.domain.FetchUserDetailsUseCase
import com.alexisdev.github_users_app.features.user_details.domain.UserDetailsRepository
import com.alexisdev.github_users_app.features.user_details.presentation.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userDetailsFeatureModule = module {
    single<UserDetailsRepository> { UserDetailsRepositoryImpl(get()) }
    factory { FetchUserDetailsUseCase(get()) }
    viewModel { UserDetailsViewModel(get()) }
}