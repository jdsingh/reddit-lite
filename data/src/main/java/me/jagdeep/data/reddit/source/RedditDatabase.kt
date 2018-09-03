package me.jagdeep.data.reddit.source

import io.reactivex.Completable
import io.reactivex.Single
import me.jagdeep.data.reddit.model.RedditPostEntity

/**
 * Interface defining methods for the database service.
 *
 * This is to be implemented by the database layer, using this interface as a way of communicating.
 */
interface RedditDatabase {

    fun getSubreddit(subreddit: String): Single<List<RedditPostEntity>>

    fun isSubredditCacheValid(subreddit: String): Single<Boolean>

    fun clearSubredditCached(subreddit: String): Completable

    fun cacheSubreddit(subreddit: String, posts: List<RedditPostEntity>)

}
