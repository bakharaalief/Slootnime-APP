package com.bakharaalief.domain

sealed class Resource<out R> private constructor() {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}