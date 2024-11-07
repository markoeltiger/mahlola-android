package com.mark.mahlola.features.auth.domain

import com.mark.mahlola.core.base.NoParams
import com.mark.mahlola.core.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface SignOut : UseCase<Boolean, NoParams>

class SignOutImpl(
    private val authRepository: AuthRepository
) : SignOut {
    override suspend fun invoke(params: NoParams): Flow<Result<Boolean>> = flow{}
}
