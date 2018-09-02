package me.jagdeep.reddit.inject.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.jagdeep.data.reddit.source.RedditDatabase
import me.jagdeep.database.RedditDatabaseImpl
import me.jagdeep.database.db.AppDatabase

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindRedditDatabase(searchDatabase: RedditDatabaseImpl): RedditDatabase

}
