package me.jagdeep.reddit.inject.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.jagdeep.data.reddit.source.RedditRemote
import me.jagdeep.reddit.BuildConfig
import me.jagdeep.remote.ApiServiceFactory
import me.jagdeep.remote.reddit.RedditApiService
import me.jagdeep.remote.reddit.RedditRemoteImpl
import java.io.File

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun file(application: Application): File {
            val file = File(application.cacheDir, "OkHttpCache")
            file.mkdirs()
            return file
        }

        @Provides
        @JvmStatic
        fun provideApiService(cacheDir: File): RedditApiService {
            return ApiServiceFactory.makeApiService(BuildConfig.DEBUG, cacheDir)
        }

    }

    @Binds
    abstract fun bindsRedditRemote(redditRemote: RedditRemoteImpl): RedditRemote

}
