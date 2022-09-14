package com.victor.myspot.login.data.repository

import com.victor.myspot.core.util.Result

interface IAccountRepository {
    suspend fun registerUser(email: String, password: String): Result<Boolean, String>
}