package me.jagdeep.remote.reddit.mapper

import me.jagdeep.data.reddit.model.RedditPostEntity
import me.jagdeep.remote.EntityMapper
import me.jagdeep.remote.reddit.model.SubRedditPost
import javax.inject.Inject

/**
 * EntityMapper for SubRedditPost and RedditPostEntity.
 */
open class RedditEntityMapper @Inject constructor() :
    EntityMapper<SubRedditPost, RedditPostEntity> {

    override fun mapToEntity(type: SubRedditPost): RedditPostEntity {
        val preview = type.data.preview.images.first()

        return RedditPostEntity(
            id = type.data.id,
            subreddit = type.data.subreddit,
            title = type.data.title,
            ups = type.data.ups,
            author = type.data.author,
            created = type.data.created_utc,
            domain = type.data.domain,
            number_of_comments = type.data.num_comments,
            thumbnail_url = type.data.thumbnail,
            thumbnail_height = type.data.thumbnail_height,
            thumbnail_width = type.data.thumbnail_width,
            source_image_url = preview.source.url,
            source_image_height = preview.source.height,
            source_image_width = preview.source.width
        )
    }

}
