package me.jagdeep.reddit.feature.main

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.airbnb.epoxy.OnModelClickListener
import me.jagdeep.reddit.feature.postviewer.RedditPostViewerActivity

class OpenRedditPostHandler(
    private val context: Context
) : OnModelClickListener<RedditPostModel_, RedditPostModel.Holder> {

    override fun onClick(
        model: RedditPostModel_,
        parentView: RedditPostModel.Holder,
        clickedView: View,
        position: Int
    ) {
        val intent = RedditPostViewerActivity.intent(context, model.redditPost())

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            context as Activity, parentView.thumbnail, parentView.thumbnail.transitionName
        )

        context.startActivity(intent, options.toBundle())
    }

}
