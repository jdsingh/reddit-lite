package me.jagdeep.reddit.feature.main

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.widget.ImageView
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.reddit.feature.postviewer.RedditPostViewerActivity

class OpenRedditPostHandler(private val context: Context) {

    operator fun invoke(thumbnail: ImageView, redditPost: RedditPost) {
        val intent = RedditPostViewerActivity.intent(context, redditPost)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            context as Activity, thumbnail, thumbnail.transitionName
        )

        context.startActivity(intent, options.toBundle())
    }

}
