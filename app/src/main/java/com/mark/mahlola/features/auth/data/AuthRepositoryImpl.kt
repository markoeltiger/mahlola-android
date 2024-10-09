package com.mark.mahlola.features.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.mark.mahlola.features.auth.domain.AuthRepository
import com.mark.mahlola.features.auth.domain.User

import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): Result<User> {
        val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        authResult.user?.let {firebaseUser ->
            return Result.success(
                User(
                    id = firebaseUser.uid,
                    email = firebaseUser.email ?: "invalid email",
                    name = firebaseUser.displayName
                )
            )
        } ?: run {
            return Result.failure(Exception("User not found"))
        }
    }

    override suspend fun signInWithPhoneNumber(
        countryCode: String,
        phoneNumber: String
    ): Result<User> {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Result<Boolean> {
        firebaseAuth.signOut()
        return Result.success(true)
    }
}