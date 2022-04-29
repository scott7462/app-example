package com.example.domain.entities

sealed class DomainResults<out T> {
    data class Success<T>(val value: T) : DomainResults<T>()
    data class Error(val error: DomainError) : DomainResults<Nothing>()
}