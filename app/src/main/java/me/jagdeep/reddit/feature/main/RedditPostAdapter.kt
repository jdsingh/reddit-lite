package me.jagdeep.reddit.feature.main

import android.annotation.SuppressLint
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_ABBREV_ALL
import android.text.format.DateUtils.MINUTE_IN_MILLIS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.reddit.R
import me.jagdeep.reddit.glide.GlideApp
import me.jagdeep.reddit.glide.roundedCorners
import me.jagdeep.reddit.glide.toPx
import javax.inject.Inject

/**
 * RedditPostAdapter and ViewHolder for RedditPost List View.
 */
class RedditPostAdapter @Inject constructor() :
    ListAdapter<RedditPost, RedditPostAdapter.RedditPostViewHolder>(RedditPostDiffCallback()) {

    var clickListener: RedditPostItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return RedditPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        if (adapterPosition == RecyclerView.NO_POSITION) return

        val item = getItem(adapterPosition)
        holder.bind(item, clickListener)
    }

    inner class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val meta = itemView.findViewById<TextView>(R.id.meta)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val ups = itemView.findViewById<TextView>(R.id.ups)
        private val comments = itemView.findViewById<TextView>(R.id.comments)

        private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)

        @SuppressLint("SetTextI18n")
        fun bind(item: RedditPost, clickListener: RedditPostItemListener?) {
            clickListener?.run {
                itemView.setOnClickListener { onRedditPostClicked(item) }
            }

            val time = DateUtils.getRelativeTimeSpanString(
                item.created * 1000,
                System.currentTimeMillis(),
                MINUTE_IN_MILLIS,
                FORMAT_ABBREV_ALL
            )

            meta.text = "u/${item.author} \u25CF $time \u25CF ${item.domain}"
            title.text = item.title
            ups.text = item.upString
            comments.text = item.commentString


            GlideApp.with(itemView.context)
                .load(item.thumbnail_url)
                .placeholder(R.drawable.reddit_logo)
                .roundedCorners(itemView.context.toPx(4))
                .into(thumbnail)
        }

    }

}
