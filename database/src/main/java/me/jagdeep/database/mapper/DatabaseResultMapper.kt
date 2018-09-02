package me.jagdeep.database.mapper

import me.jagdeep.data.reddit.model.RedditPostEntity
import me.jagdeep.database.DatabaseMapper
import me.jagdeep.database.model.RedditPost
import javax.inject.Inject

class DatabaseResultMapper @Inject constructor() :
    DatabaseMapper<RedditPostEntity, RedditPost> {

    override fun mapToDatabase(type: RedditPostEntity): RedditPost {
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

    override fun mapFromDatabase(type: RedditPost): RedditPostEntity {
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

}
