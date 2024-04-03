package com.alexisdev.github_users_app.users_api

import com.alexisdev.github_users_app.users_api.model.UserDTO
import com.alexisdev.github_users_app.users_api.model.UserDetailsDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubUsersApi {
    @GET("users")
    fun fetchUsers(
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 30
    ): Single<List<UserDTO>>

    @GET("users/{username}")
    fun fetchUserDetails(@Path("username") username: String): Single<UserDetailsDTO>

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val USERS_MAX_VALUE = 100
    }
}