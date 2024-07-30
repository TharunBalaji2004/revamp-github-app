package com.tharunbalaji.githubapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tharunbalaji.githubapp.api.GithubService
import com.tharunbalaji.githubapp.model.GithubRepo

class GithubRepository(private val githubService: GithubService) {

    private val _repos = MutableLiveData<Response<List<GithubRepo>>>()
    val repos: LiveData<Response<List<GithubRepo>>> = _repos

    suspend fun listRepos(user: String) {
        _repos.postValue(Response.Loading()) // Loading state

        val result = githubService.listRepos(user)

        if (result.isSuccessful) {
            if (result.body() != null) {
                _repos.postValue(Response.Success(data = result.body()!!))
            }
        } else {
            _repos.postValue(Response.Error(errorMessage = "API Error occurred"))
        }

    }

}