package com.alexisdev.github_users_app.features.user_details.domain

import com.alexisdev.github_users_app.core.Response
import io.reactivex.rxjava3.core.Single


interface UserDetailsRepository {
    fun fetchUserDetails(username: String): Single<UserDetails>
}