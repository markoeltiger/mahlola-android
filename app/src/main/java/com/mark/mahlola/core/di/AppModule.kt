package com.mark.mahlola.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.mark.mahlola.features.root.data.UserPreferenceRepositoryImpl
import com.mark.mahlola.features.root.domain.GetAuthState
import com.mark.mahlola.features.root.domain.GetAuthStateImpl
import com.mark.mahlola.features.root.domain.UpdateAuthState
import com.mark.mahlola.features.root.domain.UpdateAuthStateImpl
import com.mark.mahlola.features.root.domain.UserPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }


    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("user_prefs")
        }
    }

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(dataStore: DataStore<Preferences>): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(dataStore)
    }

    @Provides
    fun provideGetAuthState(userPreferenceRepository: UserPreferenceRepository): GetAuthState {
        return GetAuthStateImpl(userPreferenceRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateAuthState(userPreferenceRepository: UserPreferenceRepository): UpdateAuthState {
        return UpdateAuthStateImpl(userPreferenceRepository)
    }
}