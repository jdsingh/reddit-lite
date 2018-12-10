package me.jagdeep.domain.usecases

import io.reactivex.Observable
import me.jagdeep.domain.executor.SchedulerProvider

abstract class ObservableUseCase<T, in Params> constructor(
    private val schedulerProvider: SchedulerProvider
) {

    protected abstract fun buildUseCase(params: Params): Observable<T>

    open fun execute(params: Params): Observable<T> {
        return buildUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
    }

}
