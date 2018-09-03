package me.jagdeep.reddit.feature.main

import android.content.Context
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.reddit.feature.postviewer.RedditPostViewerActivity
import javax.inject.Inject

class OpenRedditPostHandler @Inject constructor(
    private val context: Context
) : ItemHandler {

    override operator fun invoke(redditPost: RedditPost) {
        val intent = RedditPostViewerActivity.intent(context, redditPost)
        context.startActivity(intent)
    }

}
