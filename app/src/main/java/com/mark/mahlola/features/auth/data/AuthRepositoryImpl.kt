package com.mark.mahlola.features.auth.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.mark.mahlola.MainActivity
import com.mark.mahlola.features.auth.domain.AuthRepository
import com.mark.mahlola.features.auth.domain.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose


import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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
    ): Flow<Result<Boolean>> = flow {


        try {
            val nationalPhoneNumber="${countryCode}${phoneNumber}"
            val data = sendVerificationCode(nationalPhoneNumber).collect{
               if (it.isSuccess){
                   emit(Result.success(true))
               }
                 else{
                     emit(Result.success(false))
                 }

            }

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    private fun sendVerificationCode(phoneNumber: String): Flow<Result<String>> = callbackFlow {
        val verificationCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                trySend(Result.success("Verification completed")).isSuccess
                // You may want to sign in with the credential here
            }

            override fun onVerificationFailed(e: FirebaseException) {
                trySend(Result.failure(e)) // Emit failure with exception
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                trySend(Result.success(verificationId)) // Emit success with verification ID
            }
        }

        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity( context as MainActivity)
                .setCallbacks(verificationCallback)
                .build()
        )

        awaitClose { /* Cleanup resources if needed */ }
    }
    override suspend fun signOut(): Result<Boolean> {
        firebaseAuth.signOut()
        return Result.success(true)
    }
}