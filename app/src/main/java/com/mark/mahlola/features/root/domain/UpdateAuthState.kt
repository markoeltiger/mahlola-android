package com.mark.mahlola.features.root.domain

import com.mark.mahlola.core.base.UseCase


interface UpdateAuthState : UseCase<Unit, UpdateAuthState.Params> {
    data class Params(val authState: AuthState)
}

class UpdateAuthStateImpl(
    private val userPreferenceRepository: UserPreferenceRepository
) : UpdateAuthState {
    override suspend fun invoke(params: UpdateAuthState.Params): Result<Unit> {
        return Result.success(userPreferenceRepository.setAuthState(params.authState))
    }
}