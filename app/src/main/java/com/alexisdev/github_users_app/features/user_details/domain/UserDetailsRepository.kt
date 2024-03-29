package com.alexisdev.github_users_app.features.user_details.domain

import io.reactivex.rxjava3.core.Single


interface UserDetailsRepository {
    fun fetchUserDetails(username: String): Single<UserDetails>
}