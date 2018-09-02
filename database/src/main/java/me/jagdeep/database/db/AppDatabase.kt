package me.jagdeep.database.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.jagdeep.database.dao.PostDao
import me.jagdeep.database.dao.SubredditDao
import me.jagdeep.database.model.RedditPost
import me.jagdeep.database.model.Subreddit
import javax.inject.Inject

@Database(entities = [Subreddit::class, RedditPost::class], version = 1, exportSchema = false)
abstract class AppDatabase @Inject constructor() : RoomDatabase() {

    abstract fun subredditDao(): SubredditDao

    abstract fun postDao(): PostDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "reddit.db"
                        ).build()
                    }
                    return INSTANCE as AppDatabase
                }
            }
            return INSTANCE as AppDatabase
        }
    }

}
