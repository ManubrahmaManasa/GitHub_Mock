package com.example.github_mock.di

import com.example.github_mock.data.local.GitDao
import com.example.github_mock.domain.GitRepository
import com.example.github_mock.data.network.GitRepositoryImpl
import com.example.github_mock.data.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepo(impl: NetworkService, dao: GitDao): GitRepository {
        return GitRepositoryImpl(impl,dao)
    }
}


