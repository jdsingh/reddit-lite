package me.jagdeep.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import me.jagdeep.database.model.Subreddit

@Dao
abstract class SubredditDao {

    @Query("SELECT * FROM subreddits WHERE subreddit = :subreddit")
    abstract fun getSubredditByName(subreddit: String): Single<List<Subreddit>>

    @Query("SELECT COUNT(id) FROM subreddits WHERE subreddit = :subreddit")
    abstract fun isSubredditCached(subreddit: String): Single<Int>

    @Insert
    abstract fun insertSubreddit(subreddit: Subreddit)

    @Query("DELETE from subreddits where subreddit = :subreddit")
    abstract fun deleteSubreddit(subreddit: String)

}
