package com.example.github_mock.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.github_mock.domain.models.GHRepo

@Entity(tableName = "gitRepos")
data class GHRepoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val repoURL: String
)

fun List<GHRepoEntity>.toListGHRepo(): List<GHRepo> {
    return this.map {
        GHRepo(
            id = it.id,
            name = it.name,
            repoURL = it.repoURL
        )
    }
}
