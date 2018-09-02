package me.jagdeep.domain.reddit.repository

import io.reactivex.Single
import me.jagdeep.domain.reddit.model.RedditPost

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 *
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented.
 */
interface RedditRepository {

    fun getSubreddit(subreddit: String): Single<List<RedditPost>>

}
