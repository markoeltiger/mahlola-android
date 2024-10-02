package com.mark.mahlola

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mark.mahlola.core.base.NoParams
import com.mark.mahlola.features.root.domain.AuthState
import com.mark.mahlola.features.root.domain.GetAuthState
import com.mark.mahlola.features.root.domain.UpdateAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAuthState: GetAuthState,
    private val updateAuthState: UpdateAuthState
) : ViewModel() {
    private val _authState = mutableStateOf(AuthState.UNKNOWN)
    val authState : State<AuthState> = _authState

    init {
        viewModelScope.launch {
            getAuthState(NoParams).collect {
                _authState.value = it
            }
        }
    }

    fun setAuthState(authState: AuthState) {
        viewModelScope.launch {
            updateAuthState(UpdateAuthState.Params(authState))
        }
    }
}