package com.mark.mahlola.features.auth.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(
        email: String,
        password: String,
    ): Result<User>

    suspend fun signInWithPhoneNumber(
        countryCode: String,
        phoneNumber: String,
    ): Flow<Result<Boolean>>

    suspend fun signOut(): Result<Boolean>
}