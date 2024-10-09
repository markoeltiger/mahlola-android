package com.mark.mahlola.features.auth.domain

interface AuthRepository {
    suspend fun signIn(
        email: String,
        password: String,
    ): Result<User>

    suspend fun signInWithPhoneNumber(
        countryCode: String,
        phoneNumber: String,
    ): Result<User>

    suspend fun signOut(): Result<Boolean>
}