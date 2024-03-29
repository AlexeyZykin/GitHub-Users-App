package com.alexisdev.github_users_app.users_api

import com.alexisdev.github_users_app.users_api.model.UserDTO
import com.alexisdev.github_users_app.users_api.model.UserDetailsDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubUsersApi {
    @GET("users")
    fun fetchUsers(): Observable<List<UserDTO>>

    @GET("users/{username}")
    fun fetchUserDetails(@Path("username") username: String): Single<UserDetailsDTO>
}