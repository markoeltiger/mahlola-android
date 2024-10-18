package com.mark.mahlola.features.auth.ui


import com.mark.mahlola.core.base.BaseStateViewModel
import com.mark.mahlola.features.auth.domain.SignInWithEmailPassword
import com.mark.mahlola.features.auth.domain.SignInWithPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithPhone: SignInWithPhone,
    reducer: LoginReducer
) : BaseStateViewModel<LoginAction, LoginResult, LoginEvent, LoginState, LoginReducer>(
    initialState = LoginState.DefaultState,
    reducer = reducer
){
    override fun LoginAction.process(): Flow<LoginResult> {
        return when(this) {
            is LoginAction.SignInClick -> {
                flow {
                    signInWithPhone(
                   params = SignInWithPhone.Params(countryCode = "+20", phoneNumber = phone)
                        )
                    .onSuccess {
                        emit(LoginResult.Success)
                    }.onFailure {
                        emit(LoginResult.Failure(msg = it.message ?: "Something went wrong"))
                    }
                }.onStart {
                    emit(LoginResult.Loading)
                }.catch {
                    emit(LoginResult.Failure(msg = it.message ?: "Something went wrong"))
                }
            }
        }
    }
}