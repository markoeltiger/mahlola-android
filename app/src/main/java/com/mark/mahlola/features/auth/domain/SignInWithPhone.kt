package com.mark.mahlola.features.auth.domain

import com.mark.mahlola.core.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface SignInWithPhone : UseCase<User, SignInWithPhone.Params> {
    data class Params(
        val countryCode: String,
        val phoneNumber: String)
}

class SignInWithPhoneImpl(
    private val dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : SignInWithPhone {

    override suspend fun invoke(params: SignInWithPhone.Params): Result<User> {
        return withContext(dispatcher) {
            authRepository.signInWithPhoneNumber(params.countryCode,params.phoneNumber)
        }
    }
}