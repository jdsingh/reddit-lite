package me.jagdeep.domain.usecases

import io.reactivex.Flowable
import me.jagdeep.domain.executor.SchedulerProvider

abstract class FlowableUseCase<T, in Params> constructor(
    private val schedulerProvider: SchedulerProvider
) {

    protected abstract fun buildUseCase(params: Params): Flowable<T>

    open fun execute(params: Params): Flowable<T> {
        return buildUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
    }

}
