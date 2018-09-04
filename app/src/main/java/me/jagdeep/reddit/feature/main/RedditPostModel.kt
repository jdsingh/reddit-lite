package me.jagdeep.reddit.feature.main

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.reddit.R
import me.jagdeep.reddit.glide.GlideApp
import me.jagdeep.reddit.glide.roundedCorners
import me.jagdeep.reddit.glide.toPx


@EpoxyModelClass(layout = R.layout.list_item)
abstract class RedditPostModel : EpoxyModelWithHolder<RedditPostModel.Holder>() {

    @EpoxyAttribute
    lateinit var redditPost: RedditPost

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        holder.itemView.setOnClickListener(clickListener)

        val time = DateUtils.getRelativeTimeSpanString(
            redditPost.created * 1000,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_ALL
        )

        holder.meta.text = "u/${redditPost.author} \u25CF $time \u25CF ${redditPost.domain}"
        holder.title.text = redditPost.title
        holder.ups.text = redditPost.upString
        holder.comments.text = redditPost.commentString


        GlideApp.with(holder.thumbnail)
            .load(redditPost.thumbnail_url)
            .placeholder(R.drawable.reddit_logo)
            .roundedCorners(holder.thumbnail.context.toPx(4))
            .into(holder.thumbnail)
    }

    inner class Holder : EpoxyHolder() {

        lateinit var itemView: View
        lateinit var meta: TextView
        lateinit var title: TextView
        lateinit var thumbnail: ImageView
        lateinit var ups: TextView
        lateinit var comments: TextView

        override fun bindView(itemView: View) {
            this.itemView = itemView
            meta = itemView.findViewById(R.id.meta)
            title = itemView.findViewById(R.id.title)
            ups = itemView.findViewById(R.id.ups)
            comments = itemView.findViewById(R.id.comments)
            thumbnail = itemView.findViewById(R.id.thumbnail)
        }

    }

}
