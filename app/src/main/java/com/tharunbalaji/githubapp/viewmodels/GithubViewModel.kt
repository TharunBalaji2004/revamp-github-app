package com.tharunbalaji.githubapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharunbalaji.githubapp.model.GithubRepo
import com.tharunbalaji.githubapp.repository.GithubRepository
import com.tharunbalaji.githubapp.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubViewModel(private val githubRepository: GithubRepository): ViewModel() {

    val repos: LiveData<Response<List<GithubRepo>>> = githubRepository.repos

    init {
        fetchRepos(user = "TharunBalaji2004")
    }

    private fun fetchRepos(user: String) {
        viewModelScope.launch (Dispatchers.IO) {
            githubRepository.listRepos(user)
        }
    }

}


// Asynchronous function (API Calls, DB Calls, IO calls) -> Background execution
// .IO .Main .Default .Unconfined

// Main Thread -> View