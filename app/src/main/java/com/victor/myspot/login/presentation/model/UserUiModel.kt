package com.victor.myspot.login.presentation.model

data class UserUiModel(val email: String) {
    val nickName: String
        get() = email.split("@").toString()
}
