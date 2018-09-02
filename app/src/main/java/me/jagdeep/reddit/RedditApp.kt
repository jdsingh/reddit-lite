package me.jagdeep.reddit

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.jagdeep.reddit.inject.DaggerAppComponent
import timber.log.Timber

class RedditApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)

        return appComponent
    }

}
