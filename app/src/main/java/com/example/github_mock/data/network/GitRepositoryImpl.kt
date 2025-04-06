package com.example.github_mock.data.network

import com.example.github_mock.data.local.GitDao
import com.example.github_mock.data.local.models.toListGHRepo
import com.example.github_mock.data.network.model.toDataGitList
import com.example.github_mock.domain.GitRepository
import com.example.github_mock.domain.models.GHRepo
import com.example.github_mock.domain.utils.DataError
import com.example.github_mock.domain.utils.GitResult
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val gitService: NetworkService,
    private val gitDao: GitDao
) : GitRepository {
    override suspend fun loadFromApi(options: Map<String, String>): GitResult<List<GHRepo>, DataError.Network> {

        val response = gitService.getGHRepoData(options)

        val dbList = gitDao.getAllRepos()

        if(response.isSuccessful){
            val gitRepoList = response.body()

            return if(gitRepoList != null) {
                gitDao.deleteAll()
                gitDao.insertRepos(gitRepoList.toDataGitList())
                GitResult.Success(data =  gitDao.getAllRepos().toListGHRepo())
            }else{
                GitResult.Success(dbList.toListGHRepo())
            }
        }else{
            return GitResult.Success(dbList.toListGHRepo())
        }
    }

    override suspend fun loadFromDb(): GitResult<List<GHRepo>, DataError.Local> {
        val listFormDb = gitDao.getAllRepos()
        return GitResult.Success(listFormDb.toListGHRepo())
    }
}