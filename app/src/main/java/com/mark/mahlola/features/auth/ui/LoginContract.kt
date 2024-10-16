package com.mark.mahlola.features.auth.ui

import com.mark.mahlola.core.base.MviAction
import com.mark.mahlola.core.base.MviEvent
import com.mark.mahlola.core.base.MviResult
import com.mark.mahlola.core.base.MviStateReducer
import com.mark.mahlola.core.base.MviViewState
import javax.inject.Inject

sealed class LoginAction : MviAction {
    data class SignInClick(val email: String, val password: String) : LoginAction()
}

sealed class LoginResult : MviResult {
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Failure(val msg: String) : LoginResult()
}

sealed class LoginEvent : MviEvent, LoginResult()

sealed class LoginState : MviViewState {
    object DefaultState : LoginState()
    object LoadingState : LoginState()
    object SuccessState : LoginState()
    data class ErrorState(val msg: String) : LoginState()
}

class LoginReducer @Inject constructor() : MviStateReducer<LoginState, LoginResult> {
    override fun LoginState.reduce(result: LoginResult): LoginState {
        return when (val previousState = this) {
            is LoginState.DefaultState -> previousState + result
            is LoginState.LoadingState -> previousState + result
            is LoginState.SuccessState -> previousState + result
            is LoginState.ErrorState -> previousState + result
        }
    }

    private operator fun LoginState.DefaultState.plus(result: LoginResult): LoginState {
        return when (result) {
            LoginResult.Loading -> LoginState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun LoginState.LoadingState.plus(result: LoginResult): LoginState {
        return when (result) {
            LoginResult.Success -> LoginState.SuccessState
            is LoginResult.Failure -> LoginState.ErrorState(msg = result.msg)
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun LoginState.SuccessState.plus(result: LoginResult): LoginState {
        return when (result) {
            LoginResult.Success -> LoginState.SuccessState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun LoginState.ErrorState.plus(result: LoginResult): LoginState {
        return when (result) {
            is LoginResult.Failure -> LoginState.ErrorState(msg = result.msg)
            is LoginResult.Loading -> LoginState.LoadingState
            else -> throw IllegalStateException("unsupported result $result")
        }
    }
}