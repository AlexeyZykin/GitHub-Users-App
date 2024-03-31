package com.alexisdev.github_users_app.users_api.di

import com.alexisdev.github_users_app.users_api.GitHubUsersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val usersApiModule = module {
    single { provideRetrofit(get()) }
    single { provideGitHubUsersService(get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(GitHubUsersApi.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

private fun provideGitHubUsersService(retrofit: Retrofit): GitHubUsersApi {
    return retrofit.create(GitHubUsersApi::class.java)
}

private fun provideInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY
    }
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}


