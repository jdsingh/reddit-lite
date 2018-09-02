package me.jagdeep.data.reddit.mapper

import me.jagdeep.data.DataMapper
import me.jagdeep.data.reddit.model.RedditPostEntity
import me.jagdeep.domain.reddit.model.RedditPost
import javax.inject.Inject

open class RedditPostMapper @Inject constructor() :
    DataMapper<RedditPostEntity, RedditPost> {

    override fun mapToEntity(type: RedditPost): RedditPostEntity {
        return RedditPostEntity(
            id = type.id,
            subreddit = type.subreddit,
            title = type.title,
            ups = type.ups,
            author = type.author,
            created = type.created,
            domain = type.domain,
            number_of_comments = type.number_of_comments,
            thumbnail_url = type.thumbnail_url,
            thumbnail_height = type.thumbnail_height,
            thumbnail_width = type.thumbnail_width,
            source_image_url = type.source_image_url,
            source_image_width = type.source_image_width,
            source_image_height = type.source_image_height
        )
    }

    override fun mapFromEntity(type: RedditPostEntity): RedditPost {
        return RedditPost(
            id = type.id,
            subreddit = type.subreddit,
            title = type.title,
            ups = type.ups,
            author = type.author,
            created = type.created,
            domain = type.domain,
            number_of_comments = type.number_of_comments,
            thumbnail_url = type.thumbnail_url,
            thumbnail_height = type.thumbnail_height,
            thumbnail_width = type.thumbnail_width,
            source_image_url = type.source_image_url,
            source_image_width = type.source_image_width,
            source_image_height = type.source_image_height
        )
    }

}
