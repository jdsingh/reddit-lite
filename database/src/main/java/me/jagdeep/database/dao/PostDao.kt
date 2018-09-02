package me.jagdeep.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import me.jagdeep.database.model.RedditPost

@Dao
abstract class PostDao {

    @Query("SELECT * FROM posts where id IN (:postIds)")
    abstract fun getPosts(postIds: List<String>): Single<List<RedditPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(results: List<RedditPost>)

    @Query("DELETE from posts where subreddit = :subreddit")
    abstract fun deletePosts(subreddit: String)

}
