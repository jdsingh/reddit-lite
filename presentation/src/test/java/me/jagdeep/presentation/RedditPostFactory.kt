package me.jagdeep.presentation

import me.jagdeep.domain.reddit.model.RedditPost
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object RedditPostFactory {

    fun createPost(): RedditPost {
        return RedditPost(
            id = randomString(),
            subreddit = randomString(),
            title = randomString(),
            ups = randomInt(),
            author = randomString(),
            created = randomLong(),
            domain = randomString(),
            number_of_comments = randomInt(),
            thumbnail_url = randomString(),
            thumbnail_height = randomInt(),
            thumbnail_width = randomInt(),
            source_image_url = randomString(),
            source_image_width = randomInt(),
            source_image_height = randomInt()
        )
    }

    fun createList(): List<RedditPost> {
        return listOf(
            createPost(),
            createPost(),
            createPost(),
            createPost(),
            createPost()
        )
    }

    private fun randomInt(): Int {
        return Math.random().roundToInt()
    }

    private fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    private fun randomLong(): Long {
        return Math.random().roundToLong()
    }

}
