package com.mark.mahlola.core.base

import kotlinx.coroutines.flow.Flow

interface ObservableUseCase<out T : Any, in Params : Any> {
    operator fun invoke(params: Params): Flow<T>
}
