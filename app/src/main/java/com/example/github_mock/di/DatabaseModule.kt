package com.example.github_mock.di

import android.content.Context
import androidx.room.Room
import com.example.github_mock.data.local.GitDao
import com.example.github_mock.data.local.GitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GitDatabase {
        return Room.databaseBuilder(
            context,
            GitDatabase::class.java,
            "git_repo_database",
        ).build()
    }

    @Provides
    fun provideNoteDao(gitDatabase: GitDatabase): GitDao {
        return gitDatabase.gitDao()
    }

}