package com.example.github_mock.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_mock.domain.GitRepository
import com.example.github_mock.domain.models.GHRepo
import com.example.github_mock.domain.utils.GitResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(private val repository: GitRepository):ViewModel() {
    private val _gitList = MutableLiveData<List<GHRepo>>()
    val gitList:LiveData<List<GHRepo>> = _gitList

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading:LiveData<Boolean> = _isLoading

    val queryMap = mapOf(
        "q" to "language:swift",
        "sort" to "stars",
        "order" to "desc")

    fun getGitRepoList(){
        _isLoading.postValue(true)
        viewModelScope.launch {
            when(val result = repository.getGHRepoData(queryMap)){
                is GitResult.Success -> {
                    Log.wtf("GitViewModel","getGitRepoList: ${result.data}")
                    _gitList.postValue(result.data)
                    _isLoading.postValue(false)
                }

                is GitResult.Error -> {
                    Log.wtf("GitViewModel","getGitRepoList:Network error")
                    _isLoading.postValue(false)
                }
            }
        }
    }
}