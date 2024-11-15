package com.mark.mahlola.features.home.ui

import com.mark.mahlola.core.base.BaseStateViewModel
import com.mark.mahlola.features.auth.ui.LoginAction
import com.mark.mahlola.features.auth.ui.LoginEvent
import com.mark.mahlola.features.auth.ui.LoginReducer
import com.mark.mahlola.features.auth.ui.LoginResult
import com.mark.mahlola.features.auth.ui.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    reducer: LoginReducer
) : BaseStateViewModel<LoginAction, LoginResult, LoginEvent,
    LoginState, LoginReducer>(
initialState = LoginState.DefaultState,
reducer = reducer
){
    override fun LoginAction.process(): Flow<LoginResult> {
        TODO("Not yet implemented")
    }
}