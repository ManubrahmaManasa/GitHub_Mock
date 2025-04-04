package com.example.github_mock

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.github_mock.databinding.ActivityMainBinding
import com.example.github_mock.presentation.GitAdapter
import com.example.github_mock.presentation.GitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GitAdapter
    private val viewModel: GitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = GitAdapter()

        viewModel.getGitRepoList()

        viewModel.gitList.observe(this) { gitList ->
            adapter.updateList(gitList)
            binding.rvRepo.adapter = adapter
        }

        viewModel.isLoading.observe(this) {
             binding.pbLoader.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}