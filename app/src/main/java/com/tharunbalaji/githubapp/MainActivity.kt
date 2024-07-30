package com.tharunbalaji.githubapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tharunbalaji.githubapp.adapters.RepoAdapter
import com.tharunbalaji.githubapp.api.GithubService
import com.tharunbalaji.githubapp.api.RetrofitHelper
import com.tharunbalaji.githubapp.databinding.ActivityMainBinding
import com.tharunbalaji.githubapp.repository.GithubRepository
import com.tharunbalaji.githubapp.repository.Response
import com.tharunbalaji.githubapp.viewmodels.GithubViewModel
import com.tharunbalaji.githubapp.viewmodels.GithubViewModelFactory

class MainActivity : AppCompatActivity() {

    private val githubViewModel: GithubViewModel by viewModels {
        GithubViewModelFactory(GithubRepository(RetrofitHelper.initialize()))
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repoAdapter = RepoAdapter()
        binding.rvRepos.adapter = repoAdapter
        binding.rvRepos.layoutManager = LinearLayoutManager(this)

        githubViewModel.repos.observe(this) { repos ->
            when (repos) {
                is Response.Success -> {
                    Log.d("REPOS", repos.data.toString())
                    repoAdapter.setRepos(repos.data!!)
                }
                is Response.Error -> {
                    Log.d("REPOS", "API Error")
                }
                is Response.Loading -> {
                    Log.d("REPOS", "API Loading")
                }
            }
        }

    }
}


// Loophole
// 1. Error handle
// 2. Loading state