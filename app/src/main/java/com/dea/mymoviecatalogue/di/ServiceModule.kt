package com.dea.mymoviecatalogue.di

import com.dea.mymoviecatalogue.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideGithubUserService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)
}