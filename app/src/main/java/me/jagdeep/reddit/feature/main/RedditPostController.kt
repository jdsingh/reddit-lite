package me.jagdeep.reddit.feature.main

import com.airbnb.epoxy.EpoxyController
import me.jagdeep.domain.reddit.model.RedditPost
import javax.inject.Inject

class RedditPostController @Inject constructor() : EpoxyController() {

    private var posts = emptyList<RedditPost>()

    private var clickListener: OpenRedditPostHandler? = null

    fun clickListener(openRedditPostHandler: OpenRedditPostHandler) {
        this.clickListener = openRedditPostHandler
    }

    fun submitList(list: List<RedditPost>) {
        posts = list
        requestModelBuild()
    }

    override fun buildModels() {
        for (post in posts) {
            RedditPostModel_()
                .id(post.id)
                .redditPost(post)
                .clickListener(clickListener)
                .addTo(this)
        }
    }

}
