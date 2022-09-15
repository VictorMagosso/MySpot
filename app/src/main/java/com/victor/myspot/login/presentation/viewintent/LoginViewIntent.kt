package com.victor.myspot.login.presentation.viewintent

sealed class LoginViewIntent {
    data class SignInIntent(val email: String, val password: String) : LoginViewIntent()
}