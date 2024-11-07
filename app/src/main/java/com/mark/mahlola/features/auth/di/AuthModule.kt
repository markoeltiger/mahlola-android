package com.waseem.libroom.feature.auth.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.mark.mahlola.features.auth.data.AuthRepositoryImpl
import com.mark.mahlola.features.auth.domain.AuthRepository
import com.mark.mahlola.features.auth.domain.SignInWithEmailPassword
import com.mark.mahlola.features.auth.domain.SignInWithEmailPasswordImpl
import com.mark.mahlola.features.auth.domain.SignInWithPhone
import com.mark.mahlola.features.auth.domain.SignInWithPhoneImpl
import com.mark.mahlola.features.auth.domain.SignOut
import com.mark.mahlola.features.auth.domain.SignOutImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object AuthModule {

    @Provides
    fun provideAuthRepository(@ApplicationContext  context: Context, firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(context,firebaseAuth = firebaseAuth)
    }

    @Provides
    fun provideSignInWithEmailAndPassword(
        dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): SignInWithEmailPassword {
        return SignInWithEmailPasswordImpl(dispatcher = dispatcher, authRepository = authRepository)
    }
    @Provides
    fun provideSignInWithPhoneNumber(
        dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): SignInWithPhone {
        return SignInWithPhoneImpl(dispatcher = dispatcher, authRepository = authRepository)
    }

    @Provides
    fun provideSignOut(
        authRepository: AuthRepository
    ): SignOut = SignOutImpl(authRepository = authRepository)

}