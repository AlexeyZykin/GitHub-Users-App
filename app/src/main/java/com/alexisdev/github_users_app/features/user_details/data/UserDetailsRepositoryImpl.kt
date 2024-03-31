package com.alexisdev.github_users_app.features.user_details.data

import com.alexisdev.github_users_app.core.Response
import com.alexisdev.github_users_app.features.user_details.domain.UserDetails
import com.alexisdev.github_users_app.features.user_details.domain.UserDetailsRepository
import com.alexisdev.github_users_app.users_api.GitHubUsersApi
import io.reactivex.rxjava3.core.Single

class UserDetailsRepositoryImpl(private val gitHubUsersApi: GitHubUsersApi): UserDetailsRepository {
    override fun fetchUserDetails(username: String): Single<UserDetails> {
        return gitHubUsersApi.fetchUserDetails(username)
            .map { it.toUserDetails() }
    }
}