package com.example.github_mock.data.network

import com.example.github_mock.data.network.model.GHRepoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkService {

    @GET("search/repositories")
    suspend fun getGHRepoData(
        @QueryMap options: Map<String,String>
        ):Response<GHRepoDTO>
}