package com.example.github_mock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.github_mock.data.local.models.GHRepoEntity

@Dao
interface GitDao {

    @Insert
    suspend fun insertRepos(list: List<GHRepoEntity>)

    @Query("SELECT * FROM gitRepos")
    suspend fun getAllRepos():List<GHRepoEntity>

    @Query("DELETE FROM gitRepos")
    suspend fun deleteAll()
}