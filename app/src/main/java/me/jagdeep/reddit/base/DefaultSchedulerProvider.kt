package me.jagdeep.reddit.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jagdeep.domain.executor.SchedulerProvider
import javax.inject.Inject

class DefaultSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

}
