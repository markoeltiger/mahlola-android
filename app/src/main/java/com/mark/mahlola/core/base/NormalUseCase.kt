package com.mark.mahlola.core.base

import kotlinx.coroutines.flow.Flow


interface NormalUseCase<out T : Any, in Params : Any> {

    /**
     * Should prefer this when using from JVM
     */
    suspend operator fun invoke(params: Params): Result<T>
}
