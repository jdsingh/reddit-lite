package me.jagdeep.reddit.main

import me.jagdeep.domain.reddit.model.RedditPost

interface ItemHandler {
    fun invoke(redditPost: RedditPost)
}
