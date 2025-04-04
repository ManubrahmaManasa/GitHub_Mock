package com.example.github_mock.domain.utils

sealed interface GitResult<out D,out E : GitError> {
    data class Success<out D>(val data:D) : GitResult<D, Nothing>
    data class Error<out E: GitError>(val error:E) : GitResult<Nothing, E>
}