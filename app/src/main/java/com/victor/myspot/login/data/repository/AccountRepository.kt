package com.victor.myspot.login.data.repository

import android.util.Log
import com.google.firebase.auth.*
import com.victor.myspot.core.util.Result
import kotlinx.coroutines.tasks.await

class AccountRepository(
    private val firebaseAuth: FirebaseAuth
) : IAccountRepository {
    override suspend fun registerUser(email: String, password: String): Result<Boolean, String> {
        return when (val result = mapAuthResult(email, password)) {
            is Result.Success -> Result.Success(true)
            is Result.Error -> Result.Error(result.value)
        }
    }

    private suspend fun mapAuthResult(
        email: String,
        password: String
    ): Result<AuthResult, String> {
        return try {
            Result.Success(
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Result.Success(true)
                        } else {
                            Log.d("register error", task.exception?.message.orEmpty())
                            Result.Error(ErrorCreatingAccount.GENERIC_ERROR.message)
                        }
                    }.await()
            )
        } catch (e: Exception) {
            Log.d("register error", e.message.orEmpty())
            when (e) {
                is FirebaseAuthWeakPasswordException -> Result.Error(ErrorCreatingAccount.WEAK_PASSWORD.message)
                is FirebaseAuthInvalidCredentialsException -> Result.Error(ErrorCreatingAccount.INVALID_EMAIL.message)
                is FirebaseAuthUserCollisionException -> Result.Error(ErrorCreatingAccount.ALREADY_REGISTERED.message)
                else -> Result.Error(ErrorCreatingAccount.GENERIC_ERROR.message)
            }
        }
    }
}

enum class ErrorCreatingAccount(val message: String) {
    GENERIC_ERROR("Não foi possível registrar"),
    INVALID_EMAIL("Insira um e-mail válido"),
    WEAK_PASSWORD("Essa senha é muito fraca"),
    ALREADY_REGISTERED("Usuário já está cadastrado"),
}