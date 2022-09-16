package com.victor.myspot.login.presentation.viewintent


sealed class CreateAccountViewIntent {
    data class RegisterUserIntent(
        val email: String,
        val password: String
    ) : CreateAccountViewIntent()

    object CheckUserAuth : CreateAccountViewIntent()
}