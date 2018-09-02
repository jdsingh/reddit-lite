package me.jagdeep.domain.reddit

import io.reactivex.Single
import me.jagdeep.domain.executor.PostExecutionThread
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.domain.reddit.repository.RedditRepository
import me.jagdeep.domain.usecases.SingleUseCase
import javax.inject.Inject

class GetSubredditUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val redditRepository: RedditRepository
) : SingleUseCase<List<RedditPost>, GetSubredditUseCase.Companion.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params): Single<List<RedditPost>> {
        return redditRepository.getSubreddit(params.subreddit)
    }

    companion object {
        data class Params(val subreddit: String)
    }

}
