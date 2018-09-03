package me.jagdeep.data.reddit.repository

import io.reactivex.Single
import me.jagdeep.data.reddit.mapper.RedditPostMapper
import me.jagdeep.data.reddit.source.RedditDatabase
import me.jagdeep.data.reddit.source.RedditRemote
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.domain.reddit.repository.RedditRepository
import javax.inject.Inject

/**
 * Implementation of [RedditRepository].
 */
class RedditDataRepository @Inject constructor(
    private val mapper: RedditPostMapper,
    private val remote: RedditRemote,
    private val database: RedditDatabase
) : RedditRepository {

    override fun getSubreddit(subreddit: String): Single<List<RedditPost>> {
        return database.isSubredditCacheValid(subreddit)
            .flatMap { isCacheValid ->
                if (isCacheValid) {
                    database.getSubreddit(subreddit)
                } else {
                    remote.getSubreddit(subreddit)
                        .doAfterSuccess { posts ->
                            database.cacheSubreddit(subreddit, posts)
                        }
                }
            }
            .map { entities ->
                entities.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

}
