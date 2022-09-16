package com.victor.myspot.core.util

sealed class Result<out D, out E> {
    data class Success<D>(val value: D) : Result<D, Nothing>()
    data class Error<E>(val value: E) : Result<Nothing, E>()

    inline fun getResult(
        onSuccess: (result: D) -> Unit = {},
        onFailure: (error: E) -> Unit = {},
        onFinish: (D?) -> Unit = {}
    ): D? = when (this) {
        is Success -> {
            onSuccess(value)
            onFinish(value)
            value
        }
        is Error -> {
            onFailure(value)
            onFinish(null)
            null
        }
    }
}