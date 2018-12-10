package me.jagdeep.presentation

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import me.jagdeep.domain.executor.SchedulerProvider

class TestSchedulerProvider : SchedulerProvider {

    override fun main(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

}
