package me.jagdeep.reddit.main

import me.jagdeep.domain.reddit.model.RedditPost

interface RedditPostItemListener {

    fun onRedditPostClicked(redditPost: RedditPost)

}
