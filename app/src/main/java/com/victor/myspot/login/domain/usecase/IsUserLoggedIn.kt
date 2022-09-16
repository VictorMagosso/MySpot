package com.victor.myspot.login.domain.usecase

import com.victor.myspot.login.data.repository.IAccountRepository

class IsUserLoggedIn(private val accountRepository: IAccountRepository) : IsUserLoggedInUseCase {
    override fun invoke(): Boolean =
        accountRepository.isUserLogged()
}

interface IsUserLoggedInUseCase {
    operator fun invoke(): Boolean
}
