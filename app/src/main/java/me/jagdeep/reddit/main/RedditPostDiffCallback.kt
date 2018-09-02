package me.jagdeep.reddit.main

import android.support.v7.util.DiffUtil
import me.jagdeep.domain.reddit.model.RedditPost

class RedditPostDiffCallback : DiffUtil.ItemCallback<RedditPost>() {

    override fun areItemsTheSame(oldItem: RedditPost?, newItem: RedditPost?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: RedditPost?, newItem: RedditPost?): Boolean {
        return oldItem == newItem
    }

}
