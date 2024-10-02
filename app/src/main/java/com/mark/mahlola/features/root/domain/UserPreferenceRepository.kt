package com.mark.mahlola.features.root.domain

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    fun getAuthState(): Flow<AuthState>

    suspend fun setAuthState(authState: AuthState)
}