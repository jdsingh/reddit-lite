package me.jagdeep.presentation

import me.jagdeep.domain.reddit.model.RedditPost

sealed class SubredditState {

    object Loading : SubredditState()

    data class Error(val message: String) : SubredditState()

    data class Success(val result: List<RedditPost>) : SubredditState()

}
