package com.victor.myspot.core.util

fun toUnitSuccess() = Result.Success(Unit)

fun <V, E> Result<V, E>.getOr(default: V): V {
    return when (this) {
        is Result.Success -> value
        is Result.Error -> default
    }
}

sealed class Result<out D, out E> {
    data class Success<D>(val value: D) : Result<D, Nothing>()
    data class Error<E>(val value: E) : Result<Nothing, E>()

    inline fun handleResult(
        onSuccess: (result: D) -> Unit = {},
        onError: (error: E) -> Unit = {},
        onFinish: (D?) -> Unit = {}
    ): D? = when (this) {
        is Success -> {
            onSuccess(value)
            onFinish(value)
            value
        }
        is Error -> {
            onError(value)
            onFinish(null)
            null
        }
    }

    inline fun <T> mapSuccess(transform: (D) -> T): Result<T, E> = when (this) {
        is Success -> Success(transform(value))
        is Error -> Error(value)
    }

    inline fun <T> mapError(transform: (E) -> T): Result<D, T> = when (this) {
        is Success -> Success(value)
        is Error -> Error(transform(value))
    }
}