package com.example.github_mock.presentation

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.github_mock.R
import com.example.github_mock.databinding.ActivityRepoDescriptionBinding

class RepoDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoDescriptionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDescriptionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.wvDesc.webViewClient = WebViewClient()

        val bundle = intent.extras
        val name = bundle?.getString(URL_KEY)

        name?.let {
            binding.wvDesc.loadUrl(it)
        }

    }

    companion object {
        const val URL_KEY = "url_key"
    }
}