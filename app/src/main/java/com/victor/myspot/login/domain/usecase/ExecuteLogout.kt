package com.victor.myspot.login.domain.usecase

import com.victor.myspot.login.data.repository.IAccountRepository

class ExecuteLogout(
    private val accountRepository: IAccountRepository,
) : ExecuteLogoutUseCase {
    override fun invoke() {
        accountRepository.logout()
    }
}

interface ExecuteLogoutUseCase {
    operator fun invoke()
}
