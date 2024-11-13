package com.mark.mahlola.features.home.ui

import com.mark.mahlola.core.base.MviAction

sealed class HomeAction : MviAction {
    data object GetHomeData : HomeAction()
}
