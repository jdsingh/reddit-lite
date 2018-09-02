package me.jagdeep.data.reddit.source

import io.reactivex.Single
import me.jagdeep.data.reddit.model.RedditPostEntity

/**
 * Interface defining methods for the remote service.
 *
 * This is to be implemented by the remote layer, using this interface as a way of communicating.
 */
interface RedditRemote {

    fun getSubreddit(subreddit: String): Single<List<RedditPostEntity>>

}
