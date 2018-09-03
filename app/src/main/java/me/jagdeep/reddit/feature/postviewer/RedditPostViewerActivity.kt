package me.jagdeep.reddit.feature.postviewer

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.core.widget.toast
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_reddit_post_viewer.*
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.reddit.R
import me.jagdeep.reddit.glide.GlideApp

class RedditPostViewerActivity : AppCompatActivity() {

    private val redditPost: RedditPost by lazy {
        intent.getParcelableExtra<RedditPost>(EXTRA_REDDIT_POST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_post_viewer)

        showLoading(true)

        GlideApp.with(image_view)
            .load(redditPost.source_image_url)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    showLoading(false)
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showLoading(false)
                    showError()

                    if (e != null) {
                        toast(e.localizedMessage)
                    } else {
                        toast("Something went wrong")
                    }

                    return true
                }
            })
            .fitCenter()
            .into(image_view)
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            progress_view.visibility = View.VISIBLE
        } else {
            progress_view.visibility = View.GONE
        }
    }

    private fun showError() {
        error_view.visibility = View.VISIBLE
    }

    companion object {

        private const val EXTRA_REDDIT_POST = "extra.reddit_post"

        fun intent(context: Context, redditPost: RedditPost) =
            Intent(context, RedditPostViewerActivity::class.java).apply {
                putExtra(EXTRA_REDDIT_POST, redditPost)
            }
    }

}
