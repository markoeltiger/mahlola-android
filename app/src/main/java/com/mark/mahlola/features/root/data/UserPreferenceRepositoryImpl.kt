package com.mark.mahlola.features.root.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.mark.mahlola.features.root.domain.AuthState
import com.mark.mahlola.features.root.domain.UserPreferenceRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

object PreferencesKeys {
    val AUTH_STATE = intPreferencesKey("auth_state")
}

class UserPreferenceRepositoryImpl @Inject constructor(
    private val preferenceDataStore: DataStore<Preferences>
) : UserPreferenceRepository {
    override fun getAuthState(): Flow<AuthState> = preferenceDataStore.data.map {
        AuthState.values()[it[PreferencesKeys.AUTH_STATE] ?: 0]
    }

    override suspend fun setAuthState(authState: AuthState) {
        preferenceDataStore.edit {
            it[PreferencesKeys.AUTH_STATE] = authState.ordinal
        }
    }
}