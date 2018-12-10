package me.jagdeep.domain.usecases

import io.reactivex.Single
import me.jagdeep.domain.executor.SchedulerProvider

abstract class SingleUseCase<T, in Params> constructor(
    private val schedulerProvider: SchedulerProvider
) {

    protected abstract fun buildUseCase(params: Params): Single<T>

    open fun execute(params: Params): Single<T> {
        return buildUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
    }

}
