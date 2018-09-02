package me.jagdeep.data.reddit.model

/**
 * RedditPostEntity for the data layer.
 *
 * Data layer will only accept Entity models from database and remote layers.
 */
data class RedditPostEntity(
    val id: String,
    val subreddit: String,
    val title: String,
    val ups: Int,
    val author: String,
    val created: Long,
    val domain: String,
    val number_of_comments: Int,
    val thumbnail_url: String,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val source_image_url: String,
    val source_image_width: Int,
    val source_image_height: Int
)
