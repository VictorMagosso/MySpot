package com.victor.myspot.login.data.repository

import android.util.Log
import com.google.firebase.auth.*
import com.victor.myspot.core.util.Result
import com.victor.myspot.login.data.model.User
import kotlinx.coroutines.tasks.await

class AccountRepository(
    private val firebaseAuth: FirebaseAuth
) : IAccountRepository {
    override suspend fun registerUser(email: String, password: String): Result<Boolean, String> {
        return when (val result = mapRegisterAuthResult(email, password)) {
            is Result.Success -> Result.Success(true)
            is Result.Error -> Result.Error(result.value)
        }
    }

    override suspend fun signIn(email: String, password: String): Result<User, String> {
        return when (val result = mapSignInResult(email, password)) {
            is Result.Success -> Result.Success(User(email))
            is Result.Error -> Result.Error(result.value)
        }
    }

    override fun logout() {
        if (firebaseAuth.currentUser.isLogged())
            firebaseAuth.signOut()
    }

    private suspend fun mapRegisterAuthResult(
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

    private suspend fun mapSignInResult(
        email: String,
        password: String
    ): Result<AuthResult, String> {
        return try {
            Result.Success(
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Result.Success(true)
                        } else {
                            Log.d("sign in error", task.exception?.message.orEmpty())
                            Result.Error(ErrorSigningIn.GENERIC_ERROR.message)
                        }
                    }.await()
            )
        } catch (e: Exception) {
            Log.d("sign in error", e.message.orEmpty())
            when (e) {
                is FirebaseAuthInvalidCredentialsException,
                is FirebaseAuthInvalidUserException -> Result.Error(ErrorSigningIn.INVALID_ACCOUNT.message)
                else -> Result.Error(ErrorSigningIn.GENERIC_ERROR.message)
            }
        }
    }
}

private fun FirebaseUser?.isLogged(): Boolean {
    return (this != null) || !this?.email.isNullOrEmpty()
}

enum class ErrorCreatingAccount(val message: String) {
    GENERIC_ERROR("Não foi possível registrar"),
    INVALID_EMAIL("Insira um e-mail válido"),
    WEAK_PASSWORD("Essa senha é muito fraca"),
    ALREADY_REGISTERED("Usuário já está cadastrado"),
}

enum class ErrorSigningIn(val message: String) {
    GENERIC_ERROR("Não foi possível entrar"),
    INVALID_ACCOUNT("E-mail ou senha inválidos"),
}