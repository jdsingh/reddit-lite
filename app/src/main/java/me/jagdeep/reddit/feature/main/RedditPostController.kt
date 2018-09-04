package me.jagdeep.reddit.feature.main

import com.airbnb.epoxy.TypedEpoxyController
import me.jagdeep.domain.reddit.model.RedditPost

class RedditPostController(
    private val clickListener: OpenRedditPostHandler
) : TypedEpoxyController<List<RedditPost>>() {

    override fun buildModels(data: List<RedditPost>) {
        for (post in data) {
            redditPost {
                id(post.id)
                redditPost(post)
                clickListener(clickListener)
            }
        }
    }

}
