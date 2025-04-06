package com.example.github_mock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.github_mock.data.local.models.GHRepoEntity

@Database(entities = [GHRepoEntity::class], version = 1)
abstract class GitDatabase: RoomDatabase() {
    abstract fun gitDao(): GitDao
}