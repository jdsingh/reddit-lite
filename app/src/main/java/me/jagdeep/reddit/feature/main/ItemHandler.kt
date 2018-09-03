package me.jagdeep.reddit.feature.main

import me.jagdeep.domain.reddit.model.RedditPost

interface ItemHandler {
    fun invoke(redditPost: RedditPost)
}
