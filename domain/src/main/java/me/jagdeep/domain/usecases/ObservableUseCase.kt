package me.jagdeep.domain.usecases

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.jagdeep.domain.executor.PostExecutionThread

abstract class ObservableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    val disposables = CompositeDisposable()

    protected abstract fun buildUseCase(params: Params): Observable<T>

    open fun execute(params: Params): Observable<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}
