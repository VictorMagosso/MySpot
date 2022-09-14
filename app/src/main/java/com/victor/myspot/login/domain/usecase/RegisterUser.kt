package com.victor.myspot.login.domain.usecase

import com.victor.myspot.core.util.Result
import com.victor.myspot.login.data.repository.IAccountRepository

class RegisterUser(
    private val accountRepository: IAccountRepository
) : RegisterUserUseCase {
    override suspend fun invoke(email: String, password: String): Result<Boolean, String> =
        accountRepository.registerUser(email, password)
}

interface RegisterUserUseCase {
    suspend operator fun invoke(email: String, password: String): Result<Boolean, String>
}
