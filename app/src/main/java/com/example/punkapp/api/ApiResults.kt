package com.example.punkapp.api

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val exception: String) : Result<T>()
}