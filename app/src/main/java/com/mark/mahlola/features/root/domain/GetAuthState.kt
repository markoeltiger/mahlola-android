package com.mark.mahlola.features.root.domain

import com.mark.mahlola.core.base.NoParams
import com.mark.mahlola.core.base.ObservableUseCase

interface GetAuthState : ObservableUseCase<AuthState, NoParams>

class GetAuthStateImpl(
    private val userPreferenceRepository: UserPreferenceRepository
) : GetAuthState {
    override fun invoke(params: NoParams) = userPreferenceRepository.getAuthState()
}