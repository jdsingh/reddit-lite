package me.jagdeep.reddit.inject.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.jagdeep.domain.executor.SchedulerProvider
import me.jagdeep.reddit.base.DefaultSchedulerProvider
import me.jagdeep.reddit.feature.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindSchedulerProvider(defaultSchedulerProvider: DefaultSchedulerProvider): SchedulerProvider

}
