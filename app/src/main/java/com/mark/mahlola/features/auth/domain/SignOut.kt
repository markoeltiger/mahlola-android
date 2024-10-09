package com.mark.mahlola.features.auth.domain

import com.mark.mahlola.core.base.NoParams
import com.mark.mahlola.core.base.UseCase


interface SignOut : UseCase<Boolean, NoParams>

class SignOutImpl(
    private val authRepository: AuthRepository
) : SignOut {
    override suspend fun invoke(params: NoParams): Result<Boolean> = authRepository.signOut()
}
