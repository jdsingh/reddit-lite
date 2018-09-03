package me.jagdeep.remote.reddit

import io.reactivex.Single
import me.jagdeep.data.reddit.model.RedditPostEntity
import me.jagdeep.data.reddit.source.RedditRemote
import me.jagdeep.remote.reddit.mapper.RedditEntityMapper
import javax.inject.Inject

/**
 * Implementation of [RedditRemote].
 */
class RedditRemoteImpl @Inject constructor(
    private val mapper: RedditEntityMapper,
    private val redditApiService: RedditApiService
) : RedditRemote {

    override fun getSubreddit(subreddit: String): Single<List<RedditPostEntity>> {
        return redditApiService.getSubreddit(subreddit, 50)
            .map { response ->
                response.data.children
                    // remove videos
                    .filter { !it.data.is_video }
                    // remove posts which has preview disabled
                    .filter { it.data.preview?.enabled == true }
                    .map { mapper.mapToEntity(it) }
            }
    }

}
