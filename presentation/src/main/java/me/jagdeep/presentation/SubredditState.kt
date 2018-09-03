package me.jagdeep.presentation

import me.jagdeep.domain.reddit.model.RedditPost

sealed class SubredditState {

    object Loading : SubredditState()

    class Error(val message: String) : SubredditState()

    class Success(val result: List<RedditPost>) : SubredditState()

}
