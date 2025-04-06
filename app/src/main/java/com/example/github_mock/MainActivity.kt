package com.example.github_mock

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.github_mock.databinding.ActivityMainBinding
import com.example.github_mock.presentation.GitAdapter
import com.example.github_mock.presentation.GitViewModel
import com.google.android.material.textfield.TextInputEditText
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

        viewModel.getGitRepoList("kotlin")

        /*binding.outlinedEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {

                *//*val query = binding.outlinedEditText.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.getGitRepoList(str)
                } else {
                    Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
                }*//*
                true
            } else {
                false
            }
        }*/



        viewModel.gitList.observe(this) { gitList ->
            adapter.updateList(gitList)
            binding.rvRepo.adapter = adapter
        }

        viewModel.isLoading.observe(this) {
             binding.pbLoader.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}