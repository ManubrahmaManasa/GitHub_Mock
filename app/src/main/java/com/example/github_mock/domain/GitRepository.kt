package com.example.github_mock.domain

import com.example.github_mock.domain.models.GHRepo
import com.example.github_mock.domain.utils.DataError
import com.example.github_mock.domain.utils.GitResult

interface GitRepository {
    suspend fun getGHRepoData(options: Map<String,String>): GitResult<List<GHRepo>, DataError.Network>
}