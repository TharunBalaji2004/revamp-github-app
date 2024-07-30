package com.tharunbalaji.githubapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.tharunbalaji.githubapp.R
import com.tharunbalaji.githubapp.model.GithubRepo

class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvRepoName: TextView = itemView.findViewById(R.id.tv_reponame)
    private val tvRepoDesc: TextView = itemView.findViewById(R.id.tv_repodesc)

    fun bind(repo: GithubRepo) {
        tvRepoName.text = repo.name
        tvRepoDesc.text = repo.description

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(repo.html_url.toUri())
            itemView.context.startActivity(intent)
        }
    }
}

class RepoAdapter: RecyclerView.Adapter<RepoViewHolder>() {

    private var repos: List<GithubRepo> = emptyList()

    fun setRepos(repos: List<GithubRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos.get(position))
    }
}