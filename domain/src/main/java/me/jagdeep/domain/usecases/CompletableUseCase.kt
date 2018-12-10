package me.jagdeep.domain.usecases

import io.reactivex.Completable
import me.jagdeep.domain.executor.SchedulerProvider

abstract class CompletableUseCase<in Params> constructor(
    private val schedulerProvider: SchedulerProvider
) {

    protected abstract fun buildUseCase(params: Params): Completable

    open fun execute(params: Params): Completable {
        return buildUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
    }

}
