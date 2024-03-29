package com.alexisdev.github_users_app.core

interface Mapper<T> {
    fun map(source: T)
}