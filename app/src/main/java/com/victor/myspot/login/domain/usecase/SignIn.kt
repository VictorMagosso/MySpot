package com.victor.myspot.login.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.login.data.model.User
import com.victor.myspot.login.data.repository.IAccountRepository

class SignIn(
    private val accountRepository: IAccountRepository,
) : SignInUseCase {
    override suspend fun invoke(
        email: String,
        password: String
    ): Result<User, String> =
        accountRepository.signIn(email, password)
}

interface SignInUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<User, String>
}
