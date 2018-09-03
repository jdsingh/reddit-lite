package me.jagdeep.reddit.inject.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.jagdeep.domain.executor.PostExecutionThread
import me.jagdeep.reddit.base.UiThread
import me.jagdeep.reddit.feature.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}
