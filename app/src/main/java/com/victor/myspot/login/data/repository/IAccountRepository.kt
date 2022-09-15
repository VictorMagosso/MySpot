package com.victor.myspot.login.data.repository

import com.victor.myspot.core.util.Result
import com.victor.myspot.login.data.model.User

interface IAccountRepository {
    suspend fun registerUser(email: String, password: String): Result<Boolean, String>
    suspend fun signIn(email: String, password: String): Result<User, String>
    fun logout()
}