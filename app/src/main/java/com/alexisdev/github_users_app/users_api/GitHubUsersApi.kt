package com.alexisdev.github_users_app.users_api

import com.alexisdev.github_users_app.users_api.model.UserDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface GitHubUsersApi {
    @GET("users")
    fun fetchUsers(): Observable<List<UserDTO>>
}