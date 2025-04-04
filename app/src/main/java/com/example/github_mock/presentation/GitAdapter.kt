package com.example.github_mock.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_mock.databinding.GitItemBinding
import com.example.github_mock.domain.models.GHRepo

class GitAdapter : RecyclerView.Adapter<GitAdapter.ViewHolder>() {

    private var showList: List<GHRepo> = emptyList()

    fun updateList(newList: List<GHRepo>) {
        showList = newList
    }

    inner class ViewHolder(private val binding: GitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: GHRepo) {
            binding.tvId.text = item.id.toString()
            binding.tvTitle.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitAdapter.ViewHolder {
        val binding = GitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitAdapter.ViewHolder, position: Int) =
        holder.onBind(showList[position])

    override fun getItemCount(): Int = showList.size
}