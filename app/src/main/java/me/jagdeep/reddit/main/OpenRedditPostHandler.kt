package me.jagdeep.reddit.main

import android.content.Context
import me.jagdeep.domain.reddit.model.RedditPost
import timber.log.Timber
import javax.inject.Inject

class OpenRedditPostHandler @Inject constructor(
    private val context: Context
) : ItemHandler {

    override operator fun invoke(redditPost: RedditPost) {
        Timber.d("Opening reddit post: ${redditPost.id}")
    }

}
