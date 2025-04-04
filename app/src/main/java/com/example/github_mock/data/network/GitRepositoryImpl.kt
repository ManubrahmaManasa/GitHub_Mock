package com.example.github_mock.data.network

import com.example.github_mock.data.network.model.toDomainGitList
import com.example.github_mock.domain.models.GHRepo
import com.example.github_mock.domain.GitRepository
import com.example.github_mock.domain.utils.DataError
import com.example.github_mock.domain.utils.GitResult
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(private val gitService: NetworkService): GitRepository {
    override suspend fun getGHRepoData(options: Map<String,String>): GitResult<List<GHRepo>, DataError.Network> {

        val response = gitService.getGHRepoData(options)

        if(response.isSuccessful){
            val gitRepoList = response.body()
            return if(gitRepoList != null) {
                GitResult.Success(gitRepoList.toDomainGitList())
            }else{
                GitResult.Success(emptyList())
            }
        }else{
            return GitResult.Error(DataError.Network.UNKNOWN)
        }
    }
}