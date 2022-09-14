package com.victor.myspot.login.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.victor.myspot.core.util.Result

class AccountRepository(
    private val firebaseAuth: FirebaseAuth
) : IAccountRepository {
    override suspend fun registerUser(email: String, password: String): Result<Boolean, String> {
        var result: Result<Boolean, String> = Result.Success(false)
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                result = if (task.isSuccessful) {
                    Result.Success(true)
                } else {
                    Log.d("register error", task.exception?.message.orEmpty())
                    Result.Error(ErrorCreatingAccount.GENERIC_ERROR_MESSAGE.message)
                }
            }
        return result
    }
}

enum class ErrorCreatingAccount(val message: String) {
    GENERIC_ERROR_MESSAGE("senha fraca"),
}