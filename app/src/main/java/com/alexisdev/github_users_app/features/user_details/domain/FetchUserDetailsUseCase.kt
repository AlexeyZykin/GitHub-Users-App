package com.alexisdev.github_users_app.features.user_details.domain

import com.alexisdev.github_users_app.core.Response
import io.reactivex.rxjava3.core.Single

class FetchUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) {
    fun invoke(username: String): Single<UserDetails> {
        return userDetailsRepository.fetchUserDetails(username)
    }
}