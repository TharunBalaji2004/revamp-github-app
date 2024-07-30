package com.tharunbalaji.githubapp.api

import com.tharunbalaji.githubapp.model.GithubRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String) : Response<List<GithubRepo>>
}