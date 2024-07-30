package com.tharunbalaji.githubapp.model

data class GithubRepo(
    val id: Long,
    val name: String,
    val html_url: String,
    val description: String
)
